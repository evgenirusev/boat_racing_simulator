package controllers;

import utility.Constants;
import contracts.controllers.BoatSimulatorController;
import contracts.models.Boat;
import contracts.models.BoatEngine;
import contracts.models.Race;
import database.BoatSimulatorDatabase;
import exeptions.*;
import factories.EngineFactory;
import models.boats.PowerBoat;
import models.boats.RowBoat;
import models.boats.SailBoat;
import models.boats.Yacht;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class BoatSimulatorControllerImpl implements BoatSimulatorController {
    private BoatSimulatorDatabase database;
    private Race currentRace;
    private Map<Boat, Double> finishedBoats;
    private Map<Boat, Double> unFinishedBoats;

    public BoatSimulatorControllerImpl(BoatSimulatorDatabase database) {
        this.database = database;
        this.finishedBoats = new LinkedHashMap<>();
        this.unFinishedBoats = new LinkedHashMap<>();
    }

    @Override
    public BoatSimulatorDatabase getDatabase() {
        return this.database;
    }

    @Override
    public String createBoatEngine(String model, int horsepower, int displacement, String engineType) throws DuplicateModelException {
        BoatEngine engine = EngineFactory.create(engineType, model, horsepower, displacement);

        this.database.getEngines().add(engine);
        return String.format(
                "Engine model %s with %s HP and displacement %s cm3 created successfully.",
                model, horsepower, displacement);
    }


    public void setDatabase(BoatSimulatorDatabase database) {
        this.database = database;
    }

    @Override
    public Race getCurrentRace() {
        return this.currentRace;
    }

    public void setCurrentRace(Race currentRace) {
        this.currentRace = currentRace;
    }

    public String createRowBoat(String model, int weight, int oars) throws DuplicateModelException {
        Boat boat = new RowBoat(model, weight, oars);
        this.database.getBoats().add(boat);
        return String.format("Row boat with model %s registered successfully.", model);
    }

    public String createSailBoat(String model, int weight, int sailEfficiency) throws DuplicateModelException {
        Boat boat = new SailBoat(model, weight, sailEfficiency);
        this.database.getBoats().add(boat);
        return String.format("Sail boat with model %s registered successfully.", model);
    }

    public String createPowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws NonExistantModelException, DuplicateModelException {
        BoatEngine firstEngine = this.database.getEngines().getItem(firstEngineModel);
        BoatEngine secondEngine = this.database.getEngines().getItem(secondEngineModel);
        Boat boat = new PowerBoat(model, weight, new ArrayList<BoatEngine>(){{ add(firstEngine); add(secondEngine); }});
        this.database.getBoats().add(boat);
        return String.format("Power boat with model %s registered successfully.", model);
    }

    public String createYacht(String model, int weight, String engineModel, int cargoWeight) throws NonExistantModelException, DuplicateModelException {
        BoatEngine engine = this.database.getEngines().getItem(engineModel);
        Boat boat = new Yacht(model, weight, new ArrayList<BoatEngine>(){{ add(engine);}}, cargoWeight);
        this.database.getBoats().add(boat);
        return String.format("Yacht with model %s registered successfully.", model);
    }

    public String openRace(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats) throws RaceAlreadyExistsException {
        Race race = new models.RaceImpl(distance, windSpeed, oceanCurrentSpeed, allowsMotorboats);
        this.validateRaceIsEmpty();
        this.currentRace = race;
        return String.format(
                        "A new race with distance %s meters, wind speed %sm/s and ocean current speed %s m/s has been set.",
                        distance, windSpeed, oceanCurrentSpeed);
    }

    public String signUpBoat(String model) throws NonExistantModelException, DuplicateModelException, NoSetRaceException {
        Boat boat = this.database.getBoats().getItem(model);
        this.validateRaceIsSet();
        if (!this.currentRace.getAllowsMotorboats() && boat instanceof Boat) {
            throw new IllegalArgumentException(Constants.IncorrectBoatTypeMessage);
        }
        this.currentRace.addParticipant(boat);
        return String.format("Boat with model %s has signed up for the current Race.", model);
    }

    public String startRace() throws InsufficientContestantsException, NoSetRaceException {
        this.validateRaceIsSet();
        List<Boat> participants = this.currentRace.GetParticipants();
        if (participants.size() < 3) {
            throw new InsufficientContestantsException(Constants.InsufficientContestantsMessage);
        }

        findFastest(participants);

        String[] places = {"First", "Second", "Third"};
        final int[] index = {0};

        StringBuilder result = new StringBuilder();

        this.finishedBoats
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(boat -> {
                    if (index[0] < 3) {
                        result.append(String.format("%s place: %s Model: %s Time: %s",
                                places[index[0]++],
                                boat.getKey().getClass().getSimpleName(),
                                boat.getKey().getModel(),
                                this.isFinished(boat.getValue())))
                                .append(System.lineSeparator());
                    }
                });

        for (Map.Entry<Boat, Double> doubleBoatEntry : this.unFinishedBoats.entrySet()) {
            if (index[0] == 3) {
                break;
            }
            result.append(String.format("%s place: %s Model: %s Time: Did not finish!",
                    places[index[0]++],
                    doubleBoatEntry.getKey().getClass().getSimpleName(),
                    doubleBoatEntry.getKey().getModel()))
                    .append(System.lineSeparator());
        }

        this.currentRace = null;
        this.finishedBoats.clear();
        this.unFinishedBoats.clear();

        return result.toString().trim();
    }

    private String isFinished(Double key) {
        if (key == Double.NEGATIVE_INFINITY) {
            return "Did not finish!";
        }
        return String.format("%f.2 sec", key);
    }

    @Override
    public String getStatistic() {
        //TODO Bonus Task Implement me
        throw new NotImplementedException();
    }

    private void findFastest(Collection<Boat> participants) {
        for (Boat participant : participants) {
            Double speed = participant.calculateRaceSpeed(this.currentRace);
            Double time = this.currentRace.getDistance() / speed;

            if (time <= 0) {
                this.unFinishedBoats.put(participant, time);
            } else {
                this.finishedBoats.put(participant, time);
            }
        }

        if (this.finishedBoats.size() >= 3) {
            this.unFinishedBoats.clear();
        }
    }

    private void validateRaceIsSet() throws NoSetRaceException {
        if (this.currentRace == null) {
            throw new NoSetRaceException(Constants.NoSetRaceMessage);
        }
    }

    private void validateRaceIsEmpty() throws RaceAlreadyExistsException {
        if (this.currentRace != null) {
            throw new RaceAlreadyExistsException(Constants.RaceAlreadyExistsMessage);
        }
    }
}
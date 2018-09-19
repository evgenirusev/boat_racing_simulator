package controllers;

import Utility.Constants;
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
import models.engines.JetEngine;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class BoatSimulatorControllerImpl implements BoatSimulatorController {
    private LinkedHashMap<Double, Boat> map;
    private BoatSimulatorDatabase database;
    private Race currentRace;

    public BoatSimulatorControllerImpl(BoatSimulatorDatabase database, Race currentRace) {
        this.setDatabase(database);
        this.setCurrentRace(currentRace);
    }

    public BoatSimulatorControllerImpl() {
        this.setDatabase(new BoatSimulatorDatabase());
        this.setCurrentRace(null);
    }

    @Override
    public BoatSimulatorDatabase getDatabase() {
        return this.database;
    }

    @Override
    public String CreateBoatEngine(String model, int horsepower, int displacement, String engineType) throws DuplicateModelException {
        BoatEngine engine = EngineFactory.create(engineType.toString());

        this.database.getEngines().Add(engine);
        return String.format(
                "Engine model %s with %s HP and displacement %s cm3 created successfully.",
                model,
                horsepower,
                displacement);
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
        this.database.getBoats().Add(boat);
        return String.format("Row boat with model %s registered successfully.", model);
    }

    public String createSailBoat(String model, int weight, int sailEfficiency) throws DuplicateModelException {
        Boat boat = new SailBoat(model, weight, sailEfficiency);
        this.database.getBoats().Add(boat);
        return String.format("Sail boat with model %s registered successfully.", model);
    }

    public String createPowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws NonExistantModelException, DuplicateModelException {
        BoatEngine firstEngine = this.database.getEngines().GetItem(firstEngineModel);
        BoatEngine secondEngine = this.database.getEngines().GetItem(secondEngineModel);
        Boat boat = new PowerBoat(model, weight, new ArrayList<BoatEngine>(){{ add(firstEngine); add(secondEngine); }});
        this.database.getBoats().Add(boat);
        return String.format("Power boat with model %s registered successfully.", model);
    }

    public String createYacht(String model, int weight, String engineModel, int cargoWeight) throws NonExistantModelException, DuplicateModelException {
        BoatEngine engine = this.database.getEngines().GetItem(engineModel);
        Boat boat = new Yacht(model, weight, new ArrayList<BoatEngine>(){{ add(engine);}}, cargoWeight);
        this.database.getBoats().Add(boat);
        return String.format("Yacht with model %s registered successfully.", model);
    }

    public String OpenRace(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats) throws RaceAlreadyExistsException {
//        Race race = new models.Race(distance, windSpeed, oceanCurrentSpeed, allowsMotorboats);
//        this.ValidateRaceIsEmpty();
//        this.currentRace = race;
//        return
//                String.format(
//                        "A new race with distance %s meters, wind speed %sm/s and ocean current speed %s m/s has been set.",
//                        distance, windSpeed, oceanCurrentSpeed);
        return "";
    }

    public String SignUpBoat(String model) throws NonExistantModelException, DuplicateModelException, NoSetRaceException {
        Boat boat = this.database.getBoats().GetItem(model);
        this.ValidateRaceIsSet();
        if (!this.currentRace.getAllowsMotorboats() && boat instanceof Boat) {
            throw new IllegalArgumentException(Constants.IncorrectBoatTypeMessage);
        }
        this.currentRace.AddParticipant(boat);
        return String.format("Boat with model %s has signed up for the current Race.", model);
    }

    public String StartRace() throws InsufficientContestantsException, NoSetRaceException {
        this.ValidateRaceIsSet();
        List<Boat> participants = this.currentRace.GetParticipants();
        if (participants.size() < 3) {
            throw new InsufficientContestantsException(Constants.InsufficientContestantsMessage);
        }


//        for (int i = 0; i < 3; i++) {
//            FindFastest(participants);
//        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Double, Boat> doubleMotorBoatEntry : map.entrySet()) {
            result.append(String.format("First place: %s Model: %s Time: %s",
                    doubleMotorBoatEntry.getValue().getClass().getSimpleName().toString(),
                    doubleMotorBoatEntry.getValue().getModel(),
                    isFinished(doubleMotorBoatEntry.getKey())));
        }

        this.currentRace = null;

        return result.toString();
    }

    private String isFinished(Double key) {
        if (key == Double.NEGATIVE_INFINITY) {
            return "Did not finish!";
        }
        return String.format("%f.2 sec", key);
    }

    @Override
    public String GetStatistic() {
        return null;
    }

    public String getStatistic() {
        //TODO Bonus Task Implement me
        throw new NotImplementedException();
    }

    private void FindFastest(List<Boat> participants) {
        Double bestTime = 0.0;
        Boat winner = null;
        for (Boat participant : participants) {
//            Double speed = participant.CalculateRaceSpeed(this.currentRace);
//            Double time = this.currentRace.getDistance() / speed;
//            if (time < bestTime) {
//                bestTime = time;
//                winner = participant;
//            }
        }

        map.put(bestTime, winner);
        participants.remove(winner);
    }

    private void ValidateRaceIsSet() throws NoSetRaceException {
        if (this.currentRace == null) {
            throw new NoSetRaceException(Constants.NoSetRaceMessage);
        }
    }

    private void ValidateRaceIsEmpty() throws RaceAlreadyExistsException {
        if (this.currentRace != null) {
            throw new RaceAlreadyExistsException(Constants.RaceAlreadyExistsMessage);
        }
    }
}

package contracts.controllers;

import contracts.models.Race;
import database.BoatSimulatorDatabase;
import exeptions.*;

public interface BoatSimulatorController {
    Race getCurrentRace();

    BoatSimulatorDatabase getDatabase();

    String createBoatEngine(String model, int horsepower, int displacement, String engineType) throws DuplicateModelException;

    String OpenRace(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats) throws RaceAlreadyExistsException;

    String SignUpBoat(String model) throws NonExistantModelException, DuplicateModelException, NoSetRaceException;

    String StartRace() throws InsufficientContestantsException, NoSetRaceException;

    String GetStatistic();
}

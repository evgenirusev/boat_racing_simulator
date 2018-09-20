package contracts.controllers;

import contracts.models.Race;
import database.BoatSimulatorDatabase;
import exeptions.*;

public interface BoatSimulatorController {
    Race getCurrentRace();

    BoatSimulatorDatabase getDatabase();

    String createBoatEngine(String model, int horsepower, int displacement, String engineType) throws DuplicateModelException;

    String openRace(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats) throws RaceAlreadyExistsException;

    String signUpBoat(String model) throws NonExistantModelException, DuplicateModelException, NoSetRaceException;

    String startRace() throws InsufficientContestantsException, NoSetRaceException;

    String getStatistic();
}

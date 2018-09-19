import contracts.BoatSimulatorController;
import contracts.Race;
import core.CommandHandlerImpl;
import core.Engine;
import database.BoatSimulatorDatabase;
import exeptions.*;

public class Main {
    public static void main(String[] args) {
        BoatSimulatorController ctrl = new BoatSimulatorController() {
            @Override
            public Race getCurrentRace() {
                return null;
            }

            @Override
            public BoatSimulatorDatabase getDatabase() {
                return null;
            }

            @Override
            public String CreateBoatEngine(String model, int horsepower, int displacement, String engineType) {
                return null;
            }

            @Override
            public String CreateRowBoat(String model, int weight, int oars) throws DuplicateModelException {
                return null;
            }

            @Override
            public String CreateSailBoat(String model, int weight, int sailEfficiency) throws DuplicateModelException {
                return null;
            }

            @Override
            public String CreatePowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws NonExistantModelException, DuplicateModelException {
                return null;
            }

            @Override
            public String CreateYacht(String model, int weight, String engineModel, int cargoWeight) throws
                    NonExistantModelException, DuplicateModelException {
                return null;
            }

            @Override
            public String OpenRace(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats) throws RaceAlreadyExistsException {
                return null;
            }

            @Override
            public String SignUpBoat(String model) throws NonExistantModelException, DuplicateModelException, NoSetRaceException {
                return null;
            }

            @Override
            public String StartRace() throws InsufficientContestantsException, NoSetRaceException {
                return null;
            }

            @Override
            public String GetStatistic() {
                return null;
            }
        };

        CommandHandlerImpl commandHandler = new CommandHandlerImpl(ctrl);
        Engine engine = new Engine();
        engine.Run();
    }
}


import contracts.database.Repository;
import contracts.io.Reader;
import contracts.io.Writer;
import contracts.models.Boat;
import contracts.models.BoatEngine;
import controllers.BoatSimulatorControllerImpl;
import core.CommandHandlerImpl;
import core.Engine;
import database.BoatDatabase;
import database.BoatEngineDatabase;
import database.BoatSimulatorDatabase;
import io.ConsoleInputReader;
import io.ConsoleOutputWriter;

public class Main {
    public static void main(String[] args) {
        Reader reader = new ConsoleInputReader();
        Writer writer = new ConsoleOutputWriter();

        Repository<BoatEngine> boatEngineRepository = new BoatEngineDatabase();
        Repository<Boat> boatRepository = new BoatDatabase();
        BoatSimulatorDatabase bsd = new BoatSimulatorDatabase(boatEngineRepository, boatRepository);

        BoatSimulatorControllerImpl ctrl = new BoatSimulatorControllerImpl(bsd);
        CommandHandlerImpl commandHandler = new CommandHandlerImpl(ctrl);

        Engine engine = new Engine(reader, writer, commandHandler);
        engine.Run();
    }
}

import contracts.controllers.BoatSimulatorController;
import contracts.io.Reader;
import contracts.io.Writer;
import controllers.BoatSimulatorControllerImpl;
import core.CommandHandlerImpl;
import core.Engine;
import io.ConsoleInputReader;
import io.ConsoleOutputWriter;

public class Main {
    public static void main(String[] args) {
        Reader reader = new ConsoleInputReader();
        Writer writer = new ConsoleOutputWriter();
        BoatSimulatorController ctrl = new BoatSimulatorControllerImpl();
        CommandHandlerImpl commandHandler = new CommandHandlerImpl(ctrl);

        Engine engine = new Engine(reader, writer, commandHandler);
        engine.Run();
    }
}

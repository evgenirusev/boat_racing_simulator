package core;

import contracts.controllers.BoatSimulatorController;
import contracts.controllers.CommandHandler;
import contracts.io.Reader;
import contracts.io.Writer;

import java.util.Arrays;

public class Engine {
    private String data[];

    private Reader reader;
    private Writer writer;
    private CommandHandler commandHandler;

    public Engine(Reader reader , Writer writer, CommandHandlerImpl commandHandler)
    {
        this.reader = reader;
        this.writer = writer;
        this.commandHandler = commandHandler;
    }

    public void Run()
    {
        while (true)
        {
            String userInput = this.reader.readLine();

            if (userInput.equals("End")) {
                break;
            }

            data = userInput.split("\\\\");

            String commandName = data[0];

            data = Arrays.stream(data).skip(1).toArray(String[]::new);

            interpretCommand(commandName, data);
        }
    }

    public void interpretCommand(String commandName, String[] data) {
        try
        {
            String commandResult = this.commandHandler.ExecuteCommand(commandName, data);
            System.out.println(commandResult);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
/*
CreateBoatEngine\GPH01\250\100\Jet
CreateBoatEngine\GPH02\150\150\Sterndrive
CreateRowBoat\Rower15\450\6
CreatePowerBoat\PB150\2200\GPH01\GPH02
CreateSailBoat\SailBoatPro\200\98
OpenRace\1000\10\5\true
SignUpBoat\SailBoatPro
SignUpBoat\Rower15
SignUpBoat\PB150
StartRace
End
*/
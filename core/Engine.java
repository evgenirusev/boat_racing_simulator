package core;

import contracts.BoatSimulatorController;
import contracts.CommandHandler;
import contracts.Race;
import database.BoatSimulatorDatabase;
import exeptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Engine {
    private CommandHandlerImpl commandHandler;

    public Engine(CommandHandlerImpl commandHandler)
    {
        this.commandHandler = commandHandler;
    }
    
    public CommandHandler getCommandHandler;

    public void Run()
    {
        while (true)
        {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            String name = "";
            List<String> parameters = new ArrayList<>();

            if (line.equals("End")) {
                break;
            }

            List<String> tokens = Arrays.asList(line.split("\\\\"));
            name = tokens.get(0);
            parameters = tokens.stream().skip(1).collect(Collectors.toList());

            try
            {
                String commandResult = this.commandHandler.ExecuteCommand(name, parameters);
                System.out.println(commandResult);
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }

            line = scanner.nextLine();
        }
    }
}

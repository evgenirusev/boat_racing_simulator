package core;

import contracts.*;
import database.BoatSimulatorDatabase;
import exeptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Engine {
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

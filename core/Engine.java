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

            String command = data[0];

            data = Arrays.stream(data).skip(1).toArray(String[]::new);

            interpretCommand(command);
        }
    }

    public void interpretCommand(String command) {
        try
        {
            String commandResult = this.commandHandler.ExecuteCommand(name, parameters);
            System.out.println(commandResult);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}

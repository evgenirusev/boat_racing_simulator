package core;

import annotations.Inject;
import contracts.Executable;
import contracts.controllers.BoatSimulatorController;
import contracts.controllers.CommandHandler;
import exeptions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CommandHandlerImpl implements CommandHandler {
    private String COMMAND_PATH = "commands.";
    private static final String COMMAND_SUFFIX_NAME = "Command";
    private String commandName;
    private String[] data;
    private BoatSimulatorController controller;

    public CommandHandlerImpl(BoatSimulatorController controller) {
        this.setContrller(controller);
    }

    public String parseCommandName(String commandName) {
        if (commandName.contains("SignUp")) { return "SignUpBoat"; }
        if (commandName.contains("Engine")) { return "CreateBoatEngine"; }
        if (commandName.contains("Boat") || commandName.contains("Yacht")) { return "CreateBoat"; }
        return commandName;
    }

    public String ExecuteCommand(String commandName, String[] data) throws DuplicateModelException, NonExistantModelException, RaceAlreadyExistsException, NoSetRaceException, InsufficientContestantsException {
        try {
            this.commandName = commandName;
            this.data = data;
            String commandClassName = this.parseCommandName(commandName);
            Class<?> commandClass = Class.forName(COMMAND_PATH + commandClassName + COMMAND_SUFFIX_NAME);
            Constructor<?> declaredConstructor = commandClass.getDeclaredConstructor();
            Executable command = (Executable) declaredConstructor.newInstance();

            this.injectDependencies(command);

            return command.execute();

        } catch (ClassNotFoundException |
                NoSuchMethodException |
                IllegalAccessException |
                InstantiationException |
                InvocationTargetException e) {
            throw new RuntimeException("Invalid command!");
        }
    }

    private <T> void injectDependencies(T command) throws IllegalAccessException {
        Field[] commandFields = command.getClass().getDeclaredFields();
        Field[] handlerFields = this.getClass().getDeclaredFields();

        for (Field commandField : commandFields) {
            commandField.setAccessible(true);
            if (commandField.isAnnotationPresent(Inject.class)) {
                for (Field handlerField : handlerFields) {
                    handlerField.setAccessible(true);
                    if (commandField.getType().getSimpleName().equals(handlerField.getType().getSimpleName())
                            && commandField.getType().equals(handlerField.getType()))
                    {
                        commandField.set(command, handlerField.get(this));
                    }
                }
            }
        }

    }

    public BoatSimulatorController getController() {
        return this.controller;
    }

    private void setContrller(BoatSimulatorController controller) {
        this.controller = controller;
    }
}

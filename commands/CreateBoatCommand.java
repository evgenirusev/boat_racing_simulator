package commands;

import annotations.Inject;
import contracts.Executable;
import contracts.controllers.BoatSimulatorController;
import controllers.BoatSimulatorControllerImpl;
import exeptions.DuplicateModelException;
import exeptions.NonExistantModelException;

public class CreateBoatCommand implements Executable {

    @Inject
    private String commandName;

    @Inject
    private BoatSimulatorControllerImpl controller;

    @Inject
    String[] data;

    @Override
    public String execute() throws DuplicateModelException, NonExistantModelException {
        switch(commandName) {
            case "CreateRowBoat":
                return controller.createRowBoat(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            case "CreateSailBoat":
                return controller.createSailBoat(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            case "CreatePowerBoat":
                return controller.createPowerBoat(data[0], Integer.parseInt(data[1]), data[2], data[3]);
            case "CreateYacht":
                return controller.createYacht(data[0], Integer.parseInt(data[1]), data[2], Integer.parseInt(data[1]));
                default:
                    throw new NonExistantModelException(commandName);
        }
    }
}

package commands;

import annotations.Inject;
import contracts.Executable;
import contracts.controllers.BoatSimulatorController;

public class CreateBoatCommand implements Executable {

    @Inject
    private String commandName;

    @Inject
    private BoatSimulatorController controller;

    @Inject
    String[] data;

    @Override
    public String execute() {

        switch(commandName) {
            case "CreateRowBoat":
                controller.
                break;
            case "CreateSailBoat":

                break;
            case "CreatePowerBoat":

                break;
            case "CreateYacht":

                break;
        }

        return null;
    }
}

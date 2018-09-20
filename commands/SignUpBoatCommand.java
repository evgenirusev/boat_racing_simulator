package commands;

import annotations.Inject;
import contracts.Executable;
import controllers.BoatSimulatorControllerImpl;
import exeptions.DuplicateModelException;
import exeptions.NoSetRaceException;
import exeptions.NonExistantModelException;

public class SignUpBoatCommand implements Executable {

    @Inject
    private String[] data;

    @Inject
    private BoatSimulatorControllerImpl controller;

    @Override
    public String execute() throws NonExistantModelException, NoSetRaceException, DuplicateModelException {
        return controller.signUpBoat(data[0]);
    }
}

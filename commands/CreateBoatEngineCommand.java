package commands;

import annotations.Inject;
import contracts.Executable;
import controllers.BoatSimulatorControllerImpl;
import exeptions.DuplicateModelException;

public class CreateBoatEngineCommand implements Executable {

    @Inject
    private String[] data;

    @Inject
    private BoatSimulatorControllerImpl controller;

    @Override
    public String execute() throws DuplicateModelException {
        return controller.createBoatEngine(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3]);
    }
}

package commands;

import annotations.Inject;
import contracts.Executable;
import controllers.BoatSimulatorControllerImpl;
import exeptions.InsufficientContestantsException;
import exeptions.NoSetRaceException;

public class StartRaceCommand implements Executable {

    @Inject
    private BoatSimulatorControllerImpl controller;

    @Override
    public String execute() throws InsufficientContestantsException, NoSetRaceException {
        return controller.startRace();
    }
}

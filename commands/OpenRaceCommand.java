package commands;

import annotations.Inject;
import contracts.Executable;
import controllers.BoatSimulatorControllerImpl;
import exeptions.RaceAlreadyExistsException;

public class OpenRaceCommand implements Executable {

    @Inject
    private BoatSimulatorControllerImpl controller;

    @Inject
    private String[] data;

    @Override
    public String execute() throws RaceAlreadyExistsException {

        return controller.openRace(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), Boolean.parseBoolean(data[3]));
    }
}

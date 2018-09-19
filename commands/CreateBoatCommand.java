package commands;

import annotations.Inject;
import contracts.Executable;

public class CreateBoatCommand implements Executable {

    @Inject
    String[] data;

    @Override
    public String execute() {
        return null;
    }
}

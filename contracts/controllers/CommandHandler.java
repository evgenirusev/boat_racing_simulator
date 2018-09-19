package contracts.controllers;

import exeptions.*;

import java.util.List;

public interface CommandHandler {
    String ExecuteCommand(String name, String[] data) throws DuplicateModelException, NonExistantModelException, RaceAlreadyExistsException, NoSetRaceException, InsufficientContestantsException;
}

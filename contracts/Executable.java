package contracts;

import exeptions.*;

public interface Executable {
    String execute() throws DuplicateModelException, NonExistantModelException, RaceAlreadyExistsException, NoSetRaceException, InsufficientContestantsException;
}

package contracts;

import exeptions.DuplicateModelException;
import exeptions.NonExistantModelException;
import exeptions.RaceAlreadyExistsException;

public interface Executable {
    String execute() throws DuplicateModelException, NonExistantModelException, RaceAlreadyExistsException;
}

package contracts;

import exeptions.DuplicateModelException;
import exeptions.NonExistantModelException;

public interface Executable {
    String execute() throws DuplicateModelException, NonExistantModelException;
}

package contracts;

import exeptions.DuplicateModelException;
import exeptions.NonExistantModelException;

public interface Repository<T extends Modelable> {
    void Add(T item) throws DuplicateModelException;

    T GetItem(String model) throws NonExistantModelException;
}

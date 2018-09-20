package database;

import contracts.database.Repository;
import contracts.models.Boat;
import contracts.models.Modelable;
import exeptions.DuplicateModelException;
import exeptions.NonExistantModelException;
import utility.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoatDatabase implements Repository<Boat> {
    private Map<String, Boat> boats;

    public BoatDatabase() {
        this.boats = new LinkedHashMap<>();
    }

    @Override
    public void add(Boat item) throws DuplicateModelException {
        if (this.boats.containsKey(item.getModel())) {
            throw new DuplicateModelException(Constants.DuplicateModelMessage);
        }
        boats.put(item.getModel(), item);
    }

    @Override
    public Boat getItem(String model) throws NonExistantModelException {
        return boats.get(model);
    }
}
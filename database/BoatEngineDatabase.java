package database;

import contracts.database.Repository;
import contracts.models.Boat;
import contracts.models.BoatEngine;
import exeptions.DuplicateModelException;
import exeptions.NonExistantModelException;
import utility.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoatEngineDatabase implements Repository<BoatEngine> {
    private Map<String, BoatEngine> engines;

    public BoatEngineDatabase() {
        this.engines = new LinkedHashMap<>();
    }

    @Override
    public void add(BoatEngine item) throws DuplicateModelException {
        if (this.engines.containsKey(item.getModel())) {
            throw new DuplicateModelException(Constants.DuplicateModelMessage);
        }
        engines.put(item.getModel(), item);
    }

    @Override
    public BoatEngine getItem(String model) throws NonExistantModelException {
        return engines.get(model);
    }
}
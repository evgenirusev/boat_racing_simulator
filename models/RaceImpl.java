package models;

import Utility.Constants;
import Utility.Validator;
import exeptions.DuplicateModelException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RaceImpl implements contracts.models.Race {
    private int distance;
    private int windSpeed;
    private int oceanCurrentSpeed;
    private Boolean allowsMotorBoats;
    private HashMap<String, MotorBoat> registeredBoats;

    public RaceImpl(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorBoats) {
        this.setDistance(distance);
        this.setWindSpeed(windSpeed);
        this.setOseanCurrentSpeed(oceanCurrentSpeed);
        this.setAllowsMotorBoats(allowsMotorBoats);
        this.registeredBoats = new HashMap<String, MotorBoat>();
    }

    @Override
    public int getDistance() {
        return distance;
    }

    private void setDistance(int distance) {
        Validator.ValidatePropertyValue(distance, "Distance");
        this.distance = distance;
    }

    @Override
    public int getWindSpeed() {
        return windSpeed;
    }

    private void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getOceanCurrentSpeed() {
        return oceanCurrentSpeed;
    }

    private void setOseanCurrentSpeed(int oseanCurrentSpeed) {
        this.oceanCurrentSpeed = oseanCurrentSpeed;
    }

    public Boolean getAllowsMotorboats() {
        return allowsMotorBoats;
    }

    private void setAllowsMotorBoats(Boolean allowsMotorBoats) {
        this.allowsMotorBoats = allowsMotorBoats;
    }

    protected HashMap<String, MotorBoat> getRegisteredBoats() {
        return this.registeredBoats;
    }

    public void AddParticipant(MotorBoat boat) throws DuplicateModelException {
        if (this.getRegisteredBoats().containsKey(boat.getModel())) {
            throw new DuplicateModelException(Constants.DuplicateModelMessage);
        }
        this.registeredBoats.put(boat.getModel(), boat);
    }

    public List<MotorBoat> GetParticipants() {
        return new ArrayList<MotorBoat>(this.registeredBoats.values());
    }
}
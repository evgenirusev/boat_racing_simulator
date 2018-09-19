package contracts.models;

import Utility.Validator;

public interface Boat {

    public void setWeight(int weight);

    public void setModel(String model);

    public String getModel();

    public int getWeight();
}

package contracts.models;

public interface Boat extends Modelable {

    public Double calculateRaceSpeed(Race race);

    public void setWeight(int weight);

    public void setModel(String model);

    public String getModel();

    public int getWeight();
}

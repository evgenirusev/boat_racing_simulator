package contracts.models;

public interface BoatEngine {
    int getDisplacement();

    public int getHorsepower();

    public String getModel();

    public void setModel(String model);

    int getOutput();
}

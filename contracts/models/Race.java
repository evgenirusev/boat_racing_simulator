package contracts.models;

import exeptions.DuplicateModelException;

import java.util.List;

public interface Race {
    int getDistance();

    int getWindSpeed ();

    int getOceanCurrentSpeed();

    Boolean getAllowsMotorboats ();

    void AddParticipant(MotorBoat boat) throws DuplicateModelException;

    List<MotorBoat> GetParticipants();
}

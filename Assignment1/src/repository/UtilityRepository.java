package repository;

import model.Utility;

import java.util.List;

public interface UtilityRepository {
    List<Utility> findAll();



    Utility create(Utility utility);

    Utility update(Utility utility);

    Utility findUtilityByName(String name);

}

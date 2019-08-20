package service;

import model.Utility;

import java.util.List;

public interface UtilityService {
    List<Utility> findAll();

    Utility save(Utility utility);

    Utility findUtilityByName(String name);
}

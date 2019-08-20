package service.impl;

import model.Utility;
import repository.UtilityRepository;
import service.UtilityService;

import java.util.List;

public class UtilityServiceImpl implements UtilityService {
    private UtilityRepository utilityRepository;

    public UtilityServiceImpl(UtilityRepository utilityRepository) {
        this.utilityRepository = utilityRepository;
    }
    @Override
    public List<Utility> findAll() {
        return utilityRepository.findAll();
    }

    @Override
    public Utility save(Utility utility) {
        if (utility.getId() != null) {
            return utilityRepository.update(utility);
        } else {
            return utilityRepository.create(utility);
        }
    }

    @Override
    public Utility findUtilityByName(String name) {
        return utilityRepository.findUtilityByName(name);
    }
}

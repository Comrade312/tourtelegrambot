package tourbot.service;

import tourbot.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityCrudService {

    Optional<City> getCityByName(String name);

    Optional<City> getCityById(Long id);

    List<City> getAll();

    City create(City city);

    void update(Long id, City city);

    void delete(Long id);

}

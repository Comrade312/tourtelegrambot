package tourbot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tourbot.entity.City;
import tourbot.exceptions.city.CityNotFoundException;
import tourbot.exceptions.request.BadRequestParametersException;
import tourbot.repository.CityRepository;
import tourbot.service.CityCrudService;

import java.util.List;
import java.util.Optional;

@Service
public class CityService implements CityCrudService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public Optional<City> getCityByName(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City create(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void update(Long id, City city) {
        if (city != null && id.equals(city.getId())) {
            if (!cityRepository.findById(city.getId()).isPresent()) {
                throw new CityNotFoundException(city.getId());
            }
        } else {
            throw new BadRequestParametersException("Error in data: path variable id must be not null and equal city id");
        }
        cityRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        if (!cityRepository.findById(id).isPresent()) {
            throw new CityNotFoundException(id);
        }
        cityRepository.deleteById(id);
    }
}
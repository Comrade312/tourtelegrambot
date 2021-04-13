package tourbot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tourbot.entity.City;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    Optional<City> findById(Long id);
    Optional<City> findByName(String name);
    List<City> findAll();
}

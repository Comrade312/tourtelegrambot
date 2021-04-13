package tourbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tourbot.entity.City;
import tourbot.service.CityCrudService;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityCrudService cityCrudService;

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        return new ResponseEntity<>(cityCrudService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") Long id) {
        return cityCrudService.getCityById(id)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Void> createCity(@RequestBody City newCity) {
        City city = cityCrudService.create(newCity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateCity(@RequestBody City city,
                                           @PathVariable Long id) {
        cityCrudService.update(id, city);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityCrudService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
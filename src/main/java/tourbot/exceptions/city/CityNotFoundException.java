package tourbot.exceptions.city;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException() {
        super("city not found");
    }

    public CityNotFoundException(String name) {
        super("can't find city with name = " + name);
    }

    public CityNotFoundException(Long id) {
        super("can't find city with id = " + id);
    }

}

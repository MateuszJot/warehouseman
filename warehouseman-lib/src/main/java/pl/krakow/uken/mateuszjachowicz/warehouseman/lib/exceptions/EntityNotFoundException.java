package pl.krakow.uken.mateuszjachowicz.warehouseman.lib.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends WarehausemanException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Class<?> entity, UUID id) {
        super("Not found " + entity.getSimpleName() + " with id " + id);
    }
}

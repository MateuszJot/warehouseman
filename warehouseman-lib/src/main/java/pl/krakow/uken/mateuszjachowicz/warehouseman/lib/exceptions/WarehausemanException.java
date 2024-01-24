package pl.krakow.uken.mateuszjachowicz.warehouseman.lib.exceptions;

public class WarehausemanException extends RuntimeException {
    public WarehausemanException(String message) {
        super(message);
    }

    public WarehausemanException(String message, Throwable cause) {
        super(message, cause);
    }

    public WarehausemanException(Throwable cause) {
        super(cause);
    }

    public WarehausemanException() {
    }
}

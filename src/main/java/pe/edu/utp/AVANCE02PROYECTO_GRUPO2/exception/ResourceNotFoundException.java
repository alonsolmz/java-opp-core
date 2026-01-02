package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Producto;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public Producto orElseThrow(Object o) {
        return null;
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
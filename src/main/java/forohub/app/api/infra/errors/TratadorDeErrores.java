package forohub.app.api.infra.errors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErrores {

    // Manejo de EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> manejarEntidadNoEncontrada(EntityNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Entidad no encontrada");
        response.put("detalle", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Manejo de errores de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejarErroresDeValidacion(MethodArgumentNotValidException ex) {
        Map<String, Object> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errores);
    }

    // Manejo de errores genéricos
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> manejarErroresGenericos(RuntimeException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Error interno del servidor");
        response.put("detalle", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // Manejo de otros errores no controlados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejarErroresDesconocidos(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Error inesperado");
        response.put("detalle", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}


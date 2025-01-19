package forohub.app.api.domain.dto.respuestadto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record IdRegistroRespuesta (
    @NotNull(message = "El ID no puede ser nulo")
    @Pattern(regexp = "^\\d+$", message = "El ID debe ser un número válido")  // Aseguramos que solo contenga dígitos
    @Positive(message = "El ID debe ser un número positivo") // Asegura que el ID sea mayor que 0
    Long id
) {}

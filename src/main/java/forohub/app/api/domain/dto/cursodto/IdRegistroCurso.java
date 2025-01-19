package forohub.app.api.domain.dto.cursodto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IdRegistroCurso (
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser un número positivo") // Asegura que el ID sea mayor que 0
    Long id
) {}

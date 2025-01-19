package forohub.app.api.domain.dto.topicodto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IdRegistroTopico (
    @NotNull(message = "El ID no puede ser nulo")    // Asegura que el ID no sea nulo
    @Positive(message = "El ID debe ser un número positivo")  // Asegura que el ID sea mayor que 0
    Long id
) {}

package forohub.app.api.domain.dto.cursodto;


import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(
        @NotNull Long id,
        String nombreCurso,
        String descripcion,
        String categoria,
        String status
) {}

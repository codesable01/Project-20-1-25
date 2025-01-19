package forohub.app.api.domain.dto.perfildto;


import jakarta.validation.constraints.NotNull;

public record DatosActualizarPerfil(
        @NotNull Long id,
        String nombre,
        String descripcion
) {
}

package forohub.app.api.domain.dto.perfildto;


import jakarta.validation.constraints.NotBlank;

public record DatosRegistroPerfil(
        @NotBlank String nombre,
        String descripcion
) {
}

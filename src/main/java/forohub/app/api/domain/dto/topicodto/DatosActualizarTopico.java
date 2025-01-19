package forohub.app.api.domain.dto.topicodto;


import forohub.app.api.domain.dto.cursodto.IdRegistroCurso;
import forohub.app.api.domain.dto.usuariocomercialdto.IdRegistroUsuarioComercial;
import jakarta.validation.constraints.NotNull;


public record DatosActualizarTopico(
        @NotNull Long id,
        String titulo,
        String mensaje,
        IdRegistroUsuarioComercial  autor,
        IdRegistroCurso curso,
        String status
) {
}

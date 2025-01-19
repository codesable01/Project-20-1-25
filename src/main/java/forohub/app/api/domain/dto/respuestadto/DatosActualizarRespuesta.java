package forohub.app.api.domain.dto.respuestadto;



import forohub.app.api.domain.dto.topicodto.DatosActualizarTopico;
import forohub.app.api.domain.dto.usuariocomercialdto.DatosActualizarUsuarioComercial;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(
        @NotNull Long id,
        String mensaje,
        DatosActualizarUsuarioComercial autor,
        DatosActualizarTopico topico,  // Relación con el tópico
        Boolean solucion
) 
{}


/* 
public record DatosActualizarRespuesta(
        @NotNull Long id,
        String mensaje,
        DatosActualizarUsuarioComercial autor,
        DatosActualizarTopico topico,  // Relación con el tópico
        Boolean solucion
) 
{}*/

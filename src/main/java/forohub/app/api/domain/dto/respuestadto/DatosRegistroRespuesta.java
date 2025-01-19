package forohub.app.api.domain.dto.respuestadto;



import forohub.app.api.domain.dto.usuariocomercialdto.IdRegistroUsuarioComercial;
import forohub.app.api.domain.dto.topicodto.IdRegistroTopico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
        
        //@NotNull @Valid DatosRegistroTopico topico,  // Relación con el tópico
        //@NotNull @Valid DatosRegistroUsuarioComercial autor,  // Relación con el autor (usuario comercial)
        @NotBlank String mensaje,  // Contenido de la respuesta
        @NotNull boolean solucion,  // Determina si esta respuesta es la solución
        @NotNull @Valid IdRegistroTopico topico,  // Cambio a IdRegistroTopico para el ID del tópico
        @NotNull @Valid IdRegistroUsuarioComercial autor // Cambio a IdRegistroUsuarioComercial para el ID del autor
) { }

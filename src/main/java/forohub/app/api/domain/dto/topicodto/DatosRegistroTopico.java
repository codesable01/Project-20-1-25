package forohub.app.api.domain.dto.topicodto;

import forohub.app.api.domain.dto.cursodto.IdRegistroCurso;
import forohub.app.api.domain.dto.usuariocomercialdto.IdRegistroUsuarioComercial;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        
    @NotBlank String titulo,  
    @NotBlank String mensaje,
    @NotNull  @Valid IdRegistroUsuarioComercial autor,//@Valid DatosRegistroUsuarioComercial autor,  // Ahora puede ser nulo
    @NotNull  @Valid IdRegistroCurso curso, // Solo el ID del tópico
     String status
   

) {
    }



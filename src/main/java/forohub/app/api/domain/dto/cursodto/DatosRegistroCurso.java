package forohub.app.api.domain.dto.cursodto;
import jakarta.validation.constraints.NotNull;



public record DatosRegistroCurso(
        @NotNull(message = "El nombre del curso es obligatorio") 
        String nombreCurso,
        String descripcion, 
        String categoria, 
        String status

) {}


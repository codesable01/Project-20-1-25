package forohub.app.api.domain.dto.usuariocomercialdto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record DatosActualizarUsuarioComercial(

Long id, 


        String username,

        @Email(message = "Correo electrónico inválido")
        String correoElectronico,

        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String contraseña,

        String perfiles // Puede ser un enum o lista dependiendo de tus necesidades
) {}

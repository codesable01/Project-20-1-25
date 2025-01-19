package forohub.app.api.domain.dto.usuariocomercialdto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DatosRegistroUsuarioComercial(
        @NotBlank(message = "El nombre es obligatorio")String username,
        @Email(message = "Correo electrónico inválido")
        @NotBlank(message = "El correo electrónico es obligatorio")String correoElectronico,
        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")String contraseña
    
) {
        }

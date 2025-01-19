package forohub.app.api.domain.model.usuarios;

import forohub.app.api.domain.dto.usuariocomercialdto.DatosActualizarUsuarioComercial;
import forohub.app.api.domain.dto.usuariocomercialdto.DatosRegistroUsuarioComercial;
import forohub.app.api.domain.model.perfiles.Perfil;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_comercial")
public class UsuarioComercial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
 

    @Column(nullable = false)
    private String username;

    @Column(name = "correo_electronico",unique = true, nullable = false)
    private String correoElectronico;// Campo adicional específico de UsuarioComercial

    @Column
    private String contraseña; // Ejemplo de atributo adicional

    

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id", referencedColumnName = "id")
    private Perfil perfil;



      // Constructor estándar
    public UsuarioComercial() {}

     // Constructor que acepta DatosRegistroUsuarioComercial
     public UsuarioComercial(DatosRegistroUsuarioComercial datosRegistro) {
        this.username = datosRegistro.username();
        this.correoElectronico = datosRegistro.correoElectronico();
        this.contraseña = datosRegistro.contraseña();
    }



      // Método de actualización
    public void actualizarDatos(DatosActualizarUsuarioComercial datosActualizar) {
        if (datosActualizar.username() != null) {
            this.username = datosActualizar.username();
        }
        if (datosActualizar.correoElectronico() != null) {
            this.correoElectronico = datosActualizar.correoElectronico();
        }
        if (datosActualizar.contraseña() != null) {
            this.contraseña = datosActualizar.contraseña();
        }
    }


    // Getters y setters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}



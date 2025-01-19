package forohub.app.api.domain.model.perfiles;

import forohub.app.api.domain.dto.perfildto.DatosActualizarPerfil;
import forohub.app.api.domain.dto.perfildto.DatosRegistroPerfil;
import forohub.app.api.domain.model.usuarios.UsuarioComercial;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfiles")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Visibilidad visibilidad; // Público, Privado, Semi-Privado

    @OneToOne
    @JoinColumn(name = "usuario_comercial_id", referencedColumnName = "id")
    private UsuarioComercial usuarioComercial;

        // Constructor vacío
        public Perfil() {}

        // Constructor con datos
    public Perfil(DatosRegistroPerfil datosRegistroPerfil) {
        this.nombre = datosRegistroPerfil.nombre();
        this.descripcion = datosRegistroPerfil.descripcion();
        this.visibilidad = Visibilidad.PUBLICO; // Valor predeterminado al registrar
    }


        // Método para actualizar desde DTO
    public void actualizarDatos(DatosActualizarPerfil datosActualizarPerfil) {
        if (datosActualizarPerfil.nombre() != null) {
            this.nombre = datosActualizarPerfil.nombre();
        }
        if (datosActualizarPerfil.descripcion() != null) {
            this.descripcion = datosActualizarPerfil.descripcion();
        }
      }

    

    // Getters y setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Visibilidad getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(Visibilidad visibilidad) {
        this.visibilidad = visibilidad;
    }

    public UsuarioComercial getUsuarioComercial() {
        return usuarioComercial;
    }

    public void setUsuarioComercial(UsuarioComercial usuarioComercial) {
        this.usuarioComercial = usuarioComercial;
    }
}







 enum Visibilidad {
    PUBLICO, PRIVADO, SEMI_PRIVADO
}

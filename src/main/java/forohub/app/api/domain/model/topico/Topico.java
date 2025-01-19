package forohub.app.api.domain.model.topico;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import forohub.app.api.domain.dto.topicodto.DatosActualizarTopico;
import forohub.app.api.domain.dto.topicodto.DatosRegistroTopico;
import forohub.app.api.domain.model.curso.Curso;
import forohub.app.api.domain.model.usuarios.UsuarioComercial;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "topicos")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;

    @NotNull(message = "El autor es obligatorio")
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private UsuarioComercial autor;  // Relación con UsuarioComercial (mapeado como autor)


    @NotNull(message = "El curso es obligatorio")
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;  // Relación con Curso (mapeado como curso)


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVO;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;


     // Constructor vacío (obligatorio para JPA)
     public Topico() {
    }

    // Constructor que convierte un DTO en una entidad
    public Topico(DatosRegistroTopico datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = new UsuarioComercial();  // Inicialización del UsuarioComercial (sin usar el ID por ahora)
        this.curso = new Curso(); 
        // Convertir el String del DTO al enum Status
        this.status = datos.status() != null && !datos.status().isEmpty() ? Status.valueOf(datos.status()) : Status.ACTIVO;
        // Aquí mapeamos el String del DTO al Enum
        this.fechaCreacion = LocalDateTime.now(); // Asigna la fecha de creación
    }


     // Método para actualizar datos
    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
        
        
        if (datosActualizarTopico.status() != null) {
            this.status = Status.valueOf(datosActualizarTopico.status());
        }}


    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public UsuarioComercial getAutor() {
        return autor;
    }
    
    public void setAutor(UsuarioComercial autor) {
        this.autor = autor;
    }
    

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    public enum Status {
        ACTIVO, INACTIVO
    }
}

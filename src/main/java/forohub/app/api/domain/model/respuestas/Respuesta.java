package forohub.app.api.domain.model.respuestas;

import java.time.LocalDateTime;

import forohub.app.api.domain.dto.respuestadto.DatosActualizarRespuesta;
import forohub.app.api.domain.dto.respuestadto.DatosRegistroRespuesta;
import forohub.app.api.domain.model.topico.Topico;
import forohub.app.api.domain.model.usuarios.UsuarioComercial;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "respuestas")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "topico_id", referencedColumnName = "id")
    private Topico topico; // Relación con el tópico

    @ManyToOne
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private UsuarioComercial autor; // Relación con el autor (usuario comercial)

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje; // Contenido de la respuesta

    @Column(nullable = false)
    private boolean solucion; // Determina si esta respuesta es la solución

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now(); // Asigna la fecha al momento de la creación
    }


     // Constructor vacío (obligatorio para JPA)
     public Respuesta() {
    }

      // Constructor que convierte un DTO en una entidad
      public Respuesta(DatosRegistroRespuesta datos) {
        this.mensaje = datos.mensaje();
        this.solucion = datos.solucion();
        this.topico = new Topico();  // Inicialización del Topico (sin usar el ID por ahora)
        this.autor = new UsuarioComercial();  // Inicialización del UsuarioComercial (sin usar el ID por ahora)
        this.fechaCreacion = LocalDateTime.now();
    }

    // Método para actualizar los datos
    public void actualizarDatos(DatosActualizarRespuesta datosActualizarRespuesta) {
        if (datosActualizarRespuesta.mensaje() != null) {
            this.mensaje = datosActualizarRespuesta.mensaje();
        }
        if (datosActualizarRespuesta.autor() != null) {
            // Asignación directa del autor desde el DTO
            this.autor.actualizarDatos(datosActualizarRespuesta.autor());
        }
        if (datosActualizarRespuesta.topico() != null) {
            // Actualización del curso
            this.topico.actualizarDatos(datosActualizarRespuesta.topico());
        }
        if (datosActualizarRespuesta.solucion() != null) {
            this.solucion = datosActualizarRespuesta.solucion();
        }
    }


      // Getters y Setters
      public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public UsuarioComercial getAutor() {
        return autor;
    }

    public void setAutor(UsuarioComercial autor) {
        this.autor = autor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isSolucion() {
        return solucion;
    }

    public void setSolucion(boolean solucion) {
        this.solucion = solucion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}


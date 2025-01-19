package forohub.app.api.domain.model.curso;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import forohub.app.api.domain.dto.cursodto.DatosActualizarCurso;
import forohub.app.api.domain.dto.cursodto.DatosRegistroCurso;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreCurso", nullable = false)
      private String nombreCurso;


    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_creacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // Constructor sin parámetros
    public Curso() {}

    // Constructor con parámetros
    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombreCurso = datosRegistroCurso.nombreCurso();
        this.descripcion = datosRegistroCurso.descripcion();
        this.status = Status.valueOf(datosRegistroCurso.status()); // Convertimos el String a Status
        this.fechaCreacion = LocalDateTime.now(); // Fecha de creación por defecto
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnombreCurso() {
        return nombreCurso;
    }

    public void setnombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Enumerado de status
    public enum Status {
        ACTIVO,
        INACTIVO
    }

    // Método de actualización
    public void actualizarDatos(DatosActualizarCurso datosActualizarCurso) {
        if (datosActualizarCurso.nombreCurso() != null) {
            this.nombreCurso = datosActualizarCurso.nombreCurso();
        }
        if (datosActualizarCurso.descripcion() != null) {
            this.descripcion = datosActualizarCurso.descripcion();
        }
        // Convertimos el String a Status
        this.status = Curso.Status.valueOf(datosActualizarCurso.status());
    }}


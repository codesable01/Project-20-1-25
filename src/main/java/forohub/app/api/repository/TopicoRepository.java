package forohub.app.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import forohub.app.api.domain.model.topico.Topico;



public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Métodos relacionados con POST (Creación de datos)
    boolean existsByTituloAndMensaje(String titulo, String mensaje);  // Verifica si existe un registro
    Optional<Topico> findByTituloAndMensaje(String titulo, String mensaje);  // Devuelve el registro si existe

    // Métodos relacionados con GET (Consulta de datos)
    Page<Topico> findByCurso(String curso, Pageable pageable);  // Filtrar por curso
      @Query("SELECT t FROM Topico t WHERE FUNCTION('YEAR', t.fechaCreacion) = :year")
    Page<Topico> findByFechaCreacionYear(int year, Pageable pageable);  // Filtrar por año
      //Optional<UsuarioComercial> findByTitulo(String titulo);



   Optional<Topico> findByTitulo(String titulo);

    // Consulta para filtrar por nombre de curso y año de creación
    @Query("SELECT t FROM Topico t WHERE t.curso.nombreCurso = :nombreCurso AND EXTRACT(YEAR FROM t.fechaCreacion) = :year")
    Page<Topico> findByCursoNombreCursoAndFechaCreacionYear(@Param("nombreCurso") String nombreCurso, @Param("year") int year, Pageable pageable);
    


}



package forohub.app.api.repository;

import forohub.app.api.domain.model.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Busca un curso por nombre
    Optional<Curso> findByNombreCurso(String nombreCurso);
}

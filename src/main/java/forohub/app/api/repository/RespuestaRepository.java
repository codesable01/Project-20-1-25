package forohub.app.api.repository;


import forohub.app.api.domain.model.respuestas.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    // Método para buscar respuestas relacionadas a un tópico específico
        // Operación HTTP: GET

    List<Respuesta> findByTopicoId(Long topicoId);
}

package forohub.app.api.service;

import forohub.app.api.domain.dto.cursodto.DatosActualizarCurso;
import forohub.app.api.domain.dto.cursodto.DatosRegistroCurso;
import forohub.app.api.domain.model.curso.Curso;
import forohub.app.api.repository.CursoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // Registrar un curso
    public Curso registrarCurso(DatosRegistroCurso datos) {
        Optional<Curso> cursoExistente = cursoRepository.findByNombreCurso(datos.nombreCurso());
        if (cursoExistente.isPresent()) {
            throw new IllegalArgumentException("El curso ya existe");
        }

        Curso curso = new Curso(datos);
        return cursoRepository.save(curso);
    }

    // Buscar un curso por ID
    public Curso buscarCursoPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El curso no existe"));
    }

    // Listar todos los cursos
    public List<Curso> buscarTodos() {
        return cursoRepository.findAll();
    }

    // Actualizar curso
    public Curso actualizarCurso(Long id, DatosActualizarCurso datos) {
        Curso curso = buscarCursoPorId(id);
        curso.actualizarDatos(datos);
        return cursoRepository.save(curso);
    }

    // Eliminar curso
    public void eliminarCurso(Long id) {
        Curso curso = buscarCursoPorId(id);
        cursoRepository.delete(curso);
    }
}

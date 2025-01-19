package forohub.app.api.service;

import forohub.app.api.domain.dto.topicodto.DatosActualizarTopico;
import forohub.app.api.domain.dto.topicodto.DatosRegistroTopico;
import forohub.app.api.domain.model.topico.Topico;
import forohub.app.api.domain.model.usuarios.UsuarioComercial;
import forohub.app.api.domain.model.curso.Curso;
import forohub.app.api.repository.TopicoRepository;
import forohub.app.api.repository.UsuarioComercialRepository;
import forohub.app.api.repository.CursoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioComercialRepository usuarioComercialRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository topicoRepository,
                         UsuarioComercialRepository usuarioComercialRepository,
                         CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioComercialRepository = usuarioComercialRepository;
        this.cursoRepository = cursoRepository;
    }

    // POST: Crear un nuevo tópico
    @Transactional
    public Topico crearTopico(DatosRegistroTopico datos) {
        // Verificamos si ya existe un tópico con el mismo título y mensaje
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new RuntimeException("Ya existe un tópico con el mismo título y mensaje");
        }

        // Obtener el autor por ID
        UsuarioComercial autor = usuarioComercialRepository.findById(datos.autor().id()) // Aquí se extrae el ID del autor
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        // Obtener el curso por ID
        Curso curso = cursoRepository.findById(datos.curso().id()) // Aquí se extrae el ID del curso
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Crear el tópico utilizando los datos del DTO
        Topico topico = new Topico(datos);  // Usamos el constructor de Topico con el DTO DatosRegistroTopico

        // Asignamos el autor y el curso al tópico
        topico.setAutor(autor);
        topico.setCurso(curso);

        // Guardamos el tópico en la base de datos
        return topicoRepository.save(topico);
    }

    // GET: Obtener un tópico por ID
    public Topico obtenerTopicoPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no es válido.");
        }
        return topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
    }

    // GET: Listar todos los tópicos
    public Page<Topico> listarTopicos(Pageable pageable) {
        return topicoRepository.findAll(pageable);
    }

    // GET: Listar tópicos por curso
    public Page<Topico> listarTopicosPorCurso(String curso, Pageable pageable) {
        return topicoRepository.findByCurso(curso, pageable);
    }

    // GET: Listar tópicos por año
    public Page<Topico> listarTopicosPorAnio(int year, Pageable pageable) {
        return topicoRepository.findByFechaCreacionYear(year, pageable);
    }

    // GET: Listar tópicos por curso y año
    public Page<Topico> listarTopicosPorCursoYAnio(String nombreCurso, int year, Pageable pageable) {
        return topicoRepository.findByCursoNombreCursoAndFechaCreacionYear(nombreCurso, year, pageable);
    }

    // PUT: Actualizar un tópico
    public Topico actualizarTopico(Long id, DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        topico.actualizarDatos(datosActualizarTopico);

        return topicoRepository.save(topico);
    }

    // DELETE: Eliminar un tópico
    @Transactional
    public void eliminarTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        topicoRepository.delete(topico);
    }
}

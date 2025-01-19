package forohub.app.api.controller;

import forohub.app.api.domain.dto.cursodto.DatosActualizarCurso;
import forohub.app.api.domain.dto.cursodto.DatosRegistroCurso;
import forohub.app.api.domain.model.curso.Curso;
import forohub.app.api.service.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // Registrar un curso
    @PostMapping
    public ResponseEntity<Curso> registrarCurso(@RequestBody DatosRegistroCurso datos) {
        Curso curso = cursoService.registrarCurso(datos);
        return ResponseEntity.ok(curso);
    }

    // Obtener un curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable Long id) {
        Curso curso = cursoService.buscarCursoPorId(id);
        return ResponseEntity.ok(curso);
    }

    // Obtener todos los cursos
    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoService.buscarTodos();
        return ResponseEntity.ok(cursos);
    }

    // Actualizar un curso
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @RequestBody DatosActualizarCurso datos) {
        Curso curso = cursoService.actualizarCurso(id, datos);
        return ResponseEntity.ok(curso);
    }

    // Eliminar un curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}

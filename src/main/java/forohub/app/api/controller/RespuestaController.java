package forohub.app.api.controller;

import forohub.app.api.domain.dto.respuestadto.DatosActualizarRespuesta;
import forohub.app.api.domain.dto.respuestadto.DatosRegistroRespuesta;
import forohub.app.api.domain.model.respuestas.Respuesta;
import forohub.app.api.service.RespuestaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/respuestas")
 @SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<Respuesta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos) {
        Respuesta respuesta = respuestaService.registrarRespuesta(datos);
        return ResponseEntity.ok(respuesta);
    }

     // Método GET para obtener una respuesta por su ID
     @GetMapping("/{id}")
     public ResponseEntity<Respuesta> obtenerRespuesta(@PathVariable Long id) {
         Respuesta respuesta = respuestaService.obtenerRespuesta(id);
         return ResponseEntity.ok(respuesta);
     }


     // Método GET para obtener todas las respuestas
    @GetMapping
    public ResponseEntity<List<Respuesta>> obtenerTodasRespuestas() {
        List<Respuesta> respuestas = respuestaService.obtenerTodasRespuestas();
        return ResponseEntity.ok(respuestas);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Respuesta> actualizarRespuesta(@PathVariable Long id, @RequestBody @Valid DatosActualizarRespuesta datos) {
        Respuesta respuesta = respuestaService.actualizarRespuesta(datos);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id) {
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.noContent().build();
    }
}

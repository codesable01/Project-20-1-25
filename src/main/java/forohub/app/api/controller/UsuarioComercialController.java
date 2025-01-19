package forohub.app.api.controller;


import forohub.app.api.domain.model.usuarios.UsuarioComercial;
import forohub.app.api.service.UsuarioComercialService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import forohub.app.api.domain.dto.usuariocomercialdto.DatosRegistroUsuarioComercial;
import forohub.app.api.domain.dto.usuariocomercialdto.DatosActualizarUsuarioComercial;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarioComercial")
 @SecurityRequirement(name = "bearer-key")
public class UsuarioComercialController {

    @Autowired
    private UsuarioComercialService usuarioComercialService;

    @PostMapping
    public ResponseEntity<UsuarioComercial> crearUsuarioComercial(@RequestBody @Valid DatosRegistroUsuarioComercial datos) {
        UsuarioComercial nuevoUsuario = usuarioComercialService.crearUsuario(datos);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioComercial>> listarUsuariosComerciales() {
        List<UsuarioComercial> usuarios = usuarioComercialService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioComercial> obtenerUsuarioComercial(@PathVariable Long id) {
        UsuarioComercial usuario = usuarioComercialService.obtenerUsuario(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioComercial> actualizarUsuarioComercial(
            @PathVariable Long id, @RequestBody @Valid DatosActualizarUsuarioComercial datos) {
        UsuarioComercial usuarioActualizado = usuarioComercialService.actualizarUsuario(id, datos);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarioComercial(@PathVariable Long id) {
        usuarioComercialService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}

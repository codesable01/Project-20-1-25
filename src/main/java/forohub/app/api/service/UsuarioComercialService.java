package forohub.app.api.service;


import forohub.app.api.domain.dto.usuariocomercialdto.DatosActualizarUsuarioComercial;
import forohub.app.api.domain.dto.usuariocomercialdto.DatosRegistroUsuarioComercial;
import forohub.app.api.domain.model.usuarios.UsuarioComercial;
import forohub.app.api.repository.UsuarioComercialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioComercialService {

    @Autowired
    private UsuarioComercialRepository usuarioComercialRepository;

     @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioComercial crearUsuario(DatosRegistroUsuarioComercial datos) {
        // Validaciones (opcional: puedes incluir más validaciones aquí)
        if (usuarioComercialRepository.existsBycorreoElectronico(datos.correoElectronico())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado: " + datos.correoElectronico());
        }
        if (usuarioComercialRepository.existsByusername(datos.username())) {
            throw new IllegalArgumentException("El nombre de usuario ya está registrado: " + datos.username());
        }

        // Encriptar la contraseña antes de guardarla
        String contraseñaEncriptada = passwordEncoder.encode(datos.contraseña());

        UsuarioComercial nuevoUsuario = new UsuarioComercial(datos);
        nuevoUsuario.setContraseña(contraseñaEncriptada); // Asignar la contraseña encriptada al usuario

        return usuarioComercialRepository.save(nuevoUsuario);
    }

    public List<UsuarioComercial> listarUsuarios() {
        return usuarioComercialRepository.findAll();
    }

    public UsuarioComercial obtenerUsuario(Long id) {
        return usuarioComercialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario comercial no encontrado con ID: " + id));
    }

    public UsuarioComercial actualizarUsuario(Long id, DatosActualizarUsuarioComercial datos) {
        UsuarioComercial usuarioExistente = obtenerUsuario(id);
        usuarioExistente.actualizarDatos(datos);
        return usuarioComercialRepository.save(usuarioExistente);
    }

    public void eliminarUsuario(Long id) {
        if (!usuarioComercialRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario comercial no encontrado con ID: " + id);
        }
        usuarioComercialRepository.deleteById(id);
    }
}

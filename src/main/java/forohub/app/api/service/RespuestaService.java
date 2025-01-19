package forohub.app.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import forohub.app.api.domain.dto.respuestadto.DatosActualizarRespuesta;
import forohub.app.api.domain.dto.respuestadto.DatosRegistroRespuesta;
import forohub.app.api.domain.model.respuestas.Respuesta;
import forohub.app.api.domain.model.topico.Topico;
import forohub.app.api.domain.model.usuarios.UsuarioComercial;
import forohub.app.api.repository.RespuestaRepository;
import forohub.app.api.repository.TopicoRepository;
import forohub.app.api.repository.UsuarioComercialRepository;
import jakarta.transaction.Transactional;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository; // Inyectar TopicoRepository

    @Autowired
    private UsuarioComercialRepository usuarioComercialRepository; // Inyectar UsuarioComercialRepository

    

    @Transactional
    public Respuesta registrarRespuesta(DatosRegistroRespuesta datos) {
    // Obtener las entidades Topico y UsuarioComercial usando sus IDs
    Topico topico = topicoRepository.findById(datos.topico().id())
        .orElseThrow(() -> new IllegalArgumentException("Topico no encontrado con ID: " + datos.topico().id()));
    
    UsuarioComercial autor = usuarioComercialRepository.findById(datos.autor().id())
        .orElseThrow(() -> new IllegalArgumentException("Usuario Comercial no encontrado con ID: " + datos.autor().id()));

    // Crear la respuesta, pasando el DTO para asignar valores a las propiedades
    Respuesta respuesta = new Respuesta(datos);
    
    // Asignar las entidades obtenidas al objeto respuesta
    respuesta.setTopico(topico);  // Aquí asociamos el Topico encontrado
    respuesta.setAutor(autor);    // Aquí asociamos el UsuarioComercial encontrado

    // Guardar la respuesta en el repositorio
    return respuestaRepository.save(respuesta);
}
   

    // Método para obtener una respuesta por su ID
    public Respuesta obtenerRespuesta(Long id) {
    return respuestaRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Respuesta no encontrada con ID: " + id));
}
     
      // Método para obtener todas las respuestas
    public List<Respuesta> obtenerTodasRespuestas() {
        return respuestaRepository.findAll();  // Devuelve todas las respuestas
    }


    

   
    public Respuesta actualizarRespuesta(DatosActualizarRespuesta datos) {
        Respuesta respuesta = respuestaRepository.findById(datos.id())
            .orElseThrow(() -> new IllegalArgumentException("Respuesta no encontrada con ID: " + datos.id()));
        respuesta.actualizarDatos(datos);
        return respuestaRepository.save(respuesta);
    }

    public void eliminarRespuesta(Long id) {
        if (!respuestaRepository.existsById(id)) {
            throw new IllegalArgumentException("Respuesta no encontrada con ID: " + id);
        }
        respuestaRepository.deleteById(id);
    }
}

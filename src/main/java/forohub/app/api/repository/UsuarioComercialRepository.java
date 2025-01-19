package forohub.app.api.repository;


import forohub.app.api.domain.model.usuarios.UsuarioComercial;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioComercialRepository extends JpaRepository<UsuarioComercial, Long> {
    // Método adicional para buscar por correo electrónico
    UsuarioComercial findByCorreoElectronico(String correoElectronico);

    // Método adicional para buscar por username
  //  UsuarioComercial findByUsername(String username);  // Agregar este método

    // Método adicional para buscar por username
Optional<UsuarioComercial> findByUsername(String username);  // Cambiado a Optional

boolean existsBycorreoElectronico(String correoElectronico);

boolean existsByusername(String username);


    
    
}


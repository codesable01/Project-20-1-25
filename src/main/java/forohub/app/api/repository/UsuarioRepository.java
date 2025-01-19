package forohub.app.api.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import forohub.app.api.domain.model.usuarios.Usuario;


@Repository

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
//    UserDetails findByLogin(String username);

    //Optional<Usuario> findByUsername(String username);

    UserDetails findByLogin(String username);


}

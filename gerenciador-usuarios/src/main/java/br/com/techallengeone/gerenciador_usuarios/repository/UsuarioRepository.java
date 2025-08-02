package br.com.techallengeone.gerenciador_usuarios.repository;

import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.techallengeone.gerenciador_usuarios.domain.entities.Usuario; 

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   
    Optional<Usuario> findByLogin(String login);

       Optional<Usuario> findByEmail(String email);

}

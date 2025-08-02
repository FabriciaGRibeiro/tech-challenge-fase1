package br.com.techallengeone.gerenciador_usuarios.repository;

import br.com.techallengeone.gerenciador_usuarios.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
    Optional<TipoUsuario> findByNome(String nome);
}

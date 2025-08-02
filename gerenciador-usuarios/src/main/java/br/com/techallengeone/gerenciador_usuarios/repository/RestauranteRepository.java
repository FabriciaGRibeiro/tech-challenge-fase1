package br.com.techallengeone.gerenciador_usuarios.repository;

import br.com.techallengeone.gerenciador_usuarios.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    List<Restaurante> findByUsuarioId(Long usuarioId);
    Optional<Restaurante> findByNome(String nome);
    List<Restaurante> findByTipoCozinha(String tipoCozinha);
    List<Restaurante> findByEnderecoContainingIgnoreCase(String endereco);
}
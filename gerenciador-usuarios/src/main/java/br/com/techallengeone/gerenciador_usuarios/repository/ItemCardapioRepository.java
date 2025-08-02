package br.com.techallengeone.gerenciador_usuarios.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.techallengeone.gerenciador_usuarios.model.ItemCardapio;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
    List<ItemCardapio> findByRestauranteId(Long restauranteId);
    List<ItemCardapio> findByCategoria(String categoria);
    List<ItemCardapio> findByNomeContainingIgnoreCase(String nome);
    List<ItemCardapio> findByPrecoBetween(BigDecimal precoMin, BigDecimal precoMax);
    List<ItemCardapio> findByRestauranteIdAndCategoria(Long restauranteId, String categoria);
}
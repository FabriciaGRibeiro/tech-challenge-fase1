package br.com.techallengeone.gerenciador_usuarios.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.techallengeone.gerenciador_usuarios.model.ItemCardapio;
import br.com.techallengeone.gerenciador_usuarios.model.Restaurante;
import br.com.techallengeone.gerenciador_usuarios.repository.ItemCardapioRepository;
import br.com.techallengeone.gerenciador_usuarios.repository.RestauranteRepository;

@Service
public class ItemCardapioService {

    private final ItemCardapioRepository itemCardapioRepository;
    private final RestauranteRepository restauranteRepository;

    @Autowired
    public ItemCardapioService(ItemCardapioRepository itemCardapioRepository, RestauranteRepository restauranteRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
        this.restauranteRepository = restauranteRepository;
    }

    @Transactional
    public ItemCardapio criarItemCardapio(ItemCardapio itemCardapio, Long restauranteId) {
        // Verificar se o restaurante existe
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
        
        itemCardapio.setRestaurante(restaurante);
        return itemCardapioRepository.save(itemCardapio);
    }

    @Transactional(readOnly = true)
    public List<ItemCardapio> listarTodosItensCardapio() {
        return itemCardapioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ItemCardapio> buscarItemCardapioPorId(Long id) {
        return itemCardapioRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ItemCardapio> buscarItensPorRestaurante(Long restauranteId) {
        return itemCardapioRepository.findByRestauranteId(restauranteId);
    }

    @Transactional(readOnly = true)
    public List<ItemCardapio> buscarItensPorCategoria(String categoria) {
        return itemCardapioRepository.findByCategoria(categoria);
    }

    @Transactional(readOnly = true)
    public List<ItemCardapio> buscarItensPorNome(String nome) {
        return itemCardapioRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional(readOnly = true)
    public List<ItemCardapio> buscarItensPorFaixaPreco(BigDecimal precoMin, BigDecimal precoMax) {
        return itemCardapioRepository.findByPrecoBetween(precoMin, precoMax);
    }

    @Transactional(readOnly = true)
    public List<ItemCardapio> buscarItensPorRestauranteECategoria(Long restauranteId, String categoria) {
        return itemCardapioRepository.findByRestauranteIdAndCategoria(restauranteId, categoria);
    }

    @Transactional
    public ItemCardapio atualizarItemCardapio(Long id, ItemCardapio itemCardapioAtualizado) {
        ItemCardapio itemCardapioExistente = itemCardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado"));
        
        itemCardapioExistente.setNome(itemCardapioAtualizado.getNome());
        itemCardapioExistente.setDescricao(itemCardapioAtualizado.getDescricao());
        itemCardapioExistente.setPreco(itemCardapioAtualizado.getPreco());
        itemCardapioExistente.setCategoria(itemCardapioAtualizado.getCategoria());
        
        return itemCardapioRepository.save(itemCardapioExistente);
    }

    @Transactional
    public void deletarItemCardapio(Long id) {
        ItemCardapio itemCardapio = itemCardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado"));
        itemCardapioRepository.delete(itemCardapio);
    }
}
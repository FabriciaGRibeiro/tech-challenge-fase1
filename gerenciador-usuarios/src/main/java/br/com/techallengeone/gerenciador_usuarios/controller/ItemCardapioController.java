package br.com.techallengeone.gerenciador_usuarios.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.techallengeone.gerenciador_usuarios.dto.ItemCardapioRequestDTO;
import br.com.techallengeone.gerenciador_usuarios.dto.ItemCardapioResponseDTO;
import br.com.techallengeone.gerenciador_usuarios.model.ItemCardapio;
import br.com.techallengeone.gerenciador_usuarios.service.ItemCardapioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/itens-cardapio")
public class ItemCardapioController {

    private final ItemCardapioService itemCardapioService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemCardapioController(ItemCardapioService itemCardapioService, ModelMapper modelMapper) {
        this.itemCardapioService = itemCardapioService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ItemCardapioResponseDTO> criarItemCardapio(@Valid @RequestBody ItemCardapioRequestDTO itemCardapioDTO) {
        ItemCardapio itemCardapio = modelMapper.map(itemCardapioDTO, ItemCardapio.class);
        ItemCardapio itemCardapioCriado = itemCardapioService.criarItemCardapio(itemCardapio, itemCardapioDTO.getRestauranteId());
        return new ResponseEntity<>(modelMapper.map(itemCardapioCriado, ItemCardapioResponseDTO.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ItemCardapioResponseDTO>> listarItensCardapio() {
        List<ItemCardapio> itensCardapio = itemCardapioService.listarTodosItensCardapio();
        List<ItemCardapioResponseDTO> itensCardapioDTO = itensCardapio.stream()
                .map(itemCardapio -> modelMapper.map(itemCardapio, ItemCardapioResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(itensCardapioDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioResponseDTO> buscarItemCardapioPorId(@PathVariable Long id) {
        ItemCardapio itemCardapio = itemCardapioService.buscarItemCardapioPorId(id)
                .orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado"));
        return ResponseEntity.ok(modelMapper.map(itemCardapio, ItemCardapioResponseDTO.class));
    }

    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<ItemCardapioResponseDTO>> buscarItensPorRestaurante(@PathVariable Long restauranteId) {
        List<ItemCardapio> itensCardapio = itemCardapioService.buscarItensPorRestaurante(restauranteId);
        List<ItemCardapioResponseDTO> itensCardapioDTO = itensCardapio.stream()
                .map(itemCardapio -> modelMapper.map(itemCardapio, ItemCardapioResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(itensCardapioDTO);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ItemCardapioResponseDTO>> buscarItensPorCategoria(@PathVariable String categoria) {
        List<ItemCardapio> itensCardapio = itemCardapioService.buscarItensPorCategoria(categoria);
        List<ItemCardapioResponseDTO> itensCardapioDTO = itensCardapio.stream()
                .map(itemCardapio -> modelMapper.map(itemCardapio, ItemCardapioResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(itensCardapioDTO);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<ItemCardapioResponseDTO>> buscarItensPorNome(@RequestParam String nome) {
        List<ItemCardapio> itensCardapio = itemCardapioService.buscarItensPorNome(nome);
        List<ItemCardapioResponseDTO> itensCardapioDTO = itensCardapio.stream()
                .map(itemCardapio -> modelMapper.map(itemCardapio, ItemCardapioResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(itensCardapioDTO);
    }

    @GetMapping("/preco")
    public ResponseEntity<List<ItemCardapioResponseDTO>> buscarItensPorFaixaPreco(
            @RequestParam BigDecimal precoMin, 
            @RequestParam BigDecimal precoMax) {
        List<ItemCardapio> itensCardapio = itemCardapioService.buscarItensPorFaixaPreco(precoMin, precoMax);
        List<ItemCardapioResponseDTO> itensCardapioDTO = itensCardapio.stream()
                .map(itemCardapio -> modelMapper.map(itemCardapio, ItemCardapioResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(itensCardapioDTO);
    }

    @GetMapping("/restaurante/{restauranteId}/categoria/{categoria}")
    public ResponseEntity<List<ItemCardapioResponseDTO>> buscarItensPorRestauranteECategoria(
            @PathVariable Long restauranteId, 
            @PathVariable String categoria) {
        List<ItemCardapio> itensCardapio = itemCardapioService.buscarItensPorRestauranteECategoria(restauranteId, categoria);
        List<ItemCardapioResponseDTO> itensCardapioDTO = itensCardapio.stream()
                .map(itemCardapio -> modelMapper.map(itemCardapio, ItemCardapioResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(itensCardapioDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapioResponseDTO> atualizarItemCardapio(@PathVariable Long id, @Valid @RequestBody ItemCardapioRequestDTO itemCardapioDTO) {
        ItemCardapio itemCardapio = modelMapper.map(itemCardapioDTO, ItemCardapio.class);
        ItemCardapio itemCardapioAtualizado = itemCardapioService.atualizarItemCardapio(id, itemCardapio);
        return ResponseEntity.ok(modelMapper.map(itemCardapioAtualizado, ItemCardapioResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItemCardapio(@PathVariable Long id) {
        itemCardapioService.deletarItemCardapio(id);
        return ResponseEntity.noContent().build();
    }
}
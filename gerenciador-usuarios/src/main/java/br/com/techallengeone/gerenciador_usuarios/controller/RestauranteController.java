package br.com.techallengeone.gerenciador_usuarios.controller;

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

import br.com.techallengeone.gerenciador_usuarios.dto.RestauranteRequestDTO;
import br.com.techallengeone.gerenciador_usuarios.dto.RestauranteResponseDTO;
import br.com.techallengeone.gerenciador_usuarios.model.Restaurante;
import br.com.techallengeone.gerenciador_usuarios.service.RestauranteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;
    private final ModelMapper modelMapper;

    @Autowired
    public RestauranteController(RestauranteService restauranteService, ModelMapper modelMapper) {
        this.restauranteService = restauranteService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<RestauranteResponseDTO> criarRestaurante(@Valid @RequestBody RestauranteRequestDTO restauranteDTO) {
        Restaurante restaurante = modelMapper.map(restauranteDTO, Restaurante.class);
        Restaurante restauranteCriado = restauranteService.criarRestaurante(restaurante, restauranteDTO.getUsuarioId());
        return new ResponseEntity<>(modelMapper.map(restauranteCriado, RestauranteResponseDTO.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RestauranteResponseDTO>> listarRestaurantes() {
        List<Restaurante> restaurantes = restauranteService.listarTodosRestaurantes();
        List<RestauranteResponseDTO> restaurantesDTO = restaurantes.stream()
                .map(restaurante -> modelMapper.map(restaurante, RestauranteResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurantesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> buscarRestaurantePorId(@PathVariable Long id) {
        Restaurante restaurante = restauranteService.buscarRestaurantePorId(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
        return ResponseEntity.ok(modelMapper.map(restaurante, RestauranteResponseDTO.class));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<RestauranteResponseDTO>> buscarRestaurantesPorUsuario(@PathVariable Long usuarioId) {
        List<Restaurante> restaurantes = restauranteService.buscarRestaurantesPorUsuario(usuarioId);
        List<RestauranteResponseDTO> restaurantesDTO = restaurantes.stream()
                .map(restaurante -> modelMapper.map(restaurante, RestauranteResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurantesDTO);
    }

    @GetMapping("/tipo-cozinha/{tipoCozinha}")
    public ResponseEntity<List<RestauranteResponseDTO>> buscarRestaurantesPorTipoCozinha(@PathVariable String tipoCozinha) {
        List<Restaurante> restaurantes = restauranteService.buscarRestaurantesPorTipoCozinha(tipoCozinha);
        List<RestauranteResponseDTO> restaurantesDTO = restaurantes.stream()
                .map(restaurante -> modelMapper.map(restaurante, RestauranteResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurantesDTO);
    }

    @GetMapping("/endereco")
    public ResponseEntity<List<RestauranteResponseDTO>> buscarRestaurantesPorEndereco(@RequestParam String endereco) {
        List<Restaurante> restaurantes = restauranteService.buscarRestaurantesPorEndereco(endereco);
        List<RestauranteResponseDTO> restaurantesDTO = restaurantes.stream()
                .map(restaurante -> modelMapper.map(restaurante, RestauranteResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurantesDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> atualizarRestaurante(@PathVariable Long id, @Valid @RequestBody RestauranteRequestDTO restauranteDTO) {
        Restaurante restaurante = modelMapper.map(restauranteDTO, Restaurante.class);
        Restaurante restauranteAtualizado = restauranteService.atualizarRestaurante(id, restaurante);
        return ResponseEntity.ok(modelMapper.map(restauranteAtualizado, RestauranteResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRestaurante(@PathVariable Long id) {
        restauranteService.deletarRestaurante(id);
        return ResponseEntity.noContent().build();
    }
}
package br.com.techallengeone.gerenciador_usuarios.controller;

import br.com.techallengeone.gerenciador_usuarios.dto.TipoUsuarioRequestDTO;
import br.com.techallengeone.gerenciador_usuarios.dto.TipoUsuarioResponseDTO;
import br.com.techallengeone.gerenciador_usuarios.model.TipoUsuario;
import br.com.techallengeone.gerenciador_usuarios.service.TipoUsuarioService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tipos-usuario" )
public class TipoUsuarioController {

    private final TipoUsuarioService tipoUsuarioService;
    private final ModelMapper modelMapper;

    @Autowired
    public TipoUsuarioController(TipoUsuarioService tipoUsuarioService, ModelMapper modelMapper) {
        this.tipoUsuarioService = tipoUsuarioService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<TipoUsuarioResponseDTO> criarTipoUsuario(@Valid @RequestBody TipoUsuarioRequestDTO tipoUsuarioDTO) {
        TipoUsuario tipoUsuario = modelMapper.map(tipoUsuarioDTO, TipoUsuario.class);
        TipoUsuario tipoUsuarioCriado = tipoUsuarioService.criarTipoUsuario(tipoUsuario);
        return new ResponseEntity<>(modelMapper.map(tipoUsuarioCriado, TipoUsuarioResponseDTO.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TipoUsuarioResponseDTO>> listarTiposUsuario() {
        List<TipoUsuario> tiposUsuario = tipoUsuarioService.listarTodosTiposUsuario();
        List<TipoUsuarioResponseDTO> tiposUsuarioDTO = tiposUsuario.stream()
                .map(tipoUsuario -> modelMapper.map(tipoUsuario, TipoUsuarioResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(tiposUsuarioDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioResponseDTO> buscarTipoUsuarioPorId(@PathVariable Long id) {
        TipoUsuario tipoUsuario = tipoUsuarioService.buscarTipoUsuarioPorId(id);
        return ResponseEntity.ok(modelMapper.map(tipoUsuario, TipoUsuarioResponseDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuarioResponseDTO> atualizarTipoUsuario(@PathVariable Long id, @Valid @RequestBody TipoUsuarioRequestDTO tipoUsuarioDTO) {
        TipoUsuario tipoUsuario = modelMapper.map(tipoUsuarioDTO, TipoUsuario.class);
        TipoUsuario tipoUsuarioAtualizado = tipoUsuarioService.atualizarTipoUsuario(id, tipoUsuario);
        return ResponseEntity.ok(modelMapper.map(tipoUsuarioAtualizado, TipoUsuarioResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTipoUsuario(@PathVariable Long id) {
        tipoUsuarioService.deletarTipoUsuario(id);
        return ResponseEntity.noContent().build();
    }
}

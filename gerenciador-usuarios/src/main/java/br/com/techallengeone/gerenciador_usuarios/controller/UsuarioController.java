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
import org.springframework.web.bind.annotation.RestController;

import br.com.techallengeone.gerenciador_usuarios.domain.entities.Usuario;
import br.com.techallengeone.gerenciador_usuarios.dto.LoginDTO;
import br.com.techallengeone.gerenciador_usuarios.dto.TrocaSenhaDTO;
import br.com.techallengeone.gerenciador_usuarios.dto.UsuarioCreateDTO;
import br.com.techallengeone.gerenciador_usuarios.dto.UsuarioResponseDTO;
import br.com.techallengeone.gerenciador_usuarios.dto.UsuarioUpdateDTO;
import br.com.techallengeone.gerenciador_usuarios.service.UsuarioService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/usuarios" ) 
public class UsuarioController {

    private final UsuarioService usuarioService; 
    private final ModelMapper modelMapper; 

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        this.modelMapper = new ModelMapper(); 
    }

    // Atendimento para CRIAR um usuário 
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = modelMapper.map(usuarioCreateDTO, Usuario.class);
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        UsuarioResponseDTO responseDTO = modelMapper.map(novoUsuario, UsuarioResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Atendimento para BUSCAR um usuário pelo ID 
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id)
                .map(usuario -> ResponseEntity.ok(modelMapper.map(usuario, UsuarioResponseDTO.class))) 
                .orElse(ResponseEntity.notFound().build()); 
    }

    // Atendimento para LISTAR todos os usuários 
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodosUsuarios();
        List<UsuarioResponseDTO> responseDTOs = usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // Atendimento para ATUALIZAR um usuário 
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = modelMapper.map(usuarioUpdateDTO, Usuario.class);
        try {
            Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuario);
            UsuarioResponseDTO responseDTO = modelMapper.map(usuarioAtualizado, UsuarioResponseDTO.class);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) { 
            return ResponseEntity.notFound().build();
        }
    }

    // Atendimento para DELETAR um usuário 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        try {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) { 
            return ResponseEntity.notFound().build();
        }
    }

    // Atendimento para TROCAR SENHA (
    @PostMapping("/{id}/trocar-senha")
    public ResponseEntity<Void> trocarSenha(@PathVariable Long id, @Valid @RequestBody TrocaSenhaDTO trocaSenhaDTO) {
        try {
            usuarioService.trocarSenha(id, trocaSenhaDTO.getNovaSenha());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) { 
            return ResponseEntity.notFound().build();
        }
    }

    // Atendimento para VALIDAR LOGIN 
    @PostMapping("/login")
    public ResponseEntity<String> validarLogin(@Valid @RequestBody LoginDTO loginDTO) {
        boolean loginValido = usuarioService.validarLogin(loginDTO.getLogin(), loginDTO.getSenha());
        if (loginValido) {
            return ResponseEntity.ok("Login bem-sucedido.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha inválidos.");
        }
    }
}

package br.com.techallengeone.gerenciador_usuarios.service; 

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.techallengeone.gerenciador_usuarios.model.Usuario;
import br.com.techallengeone.gerenciador_usuarios.repository.UsuarioRepository;

@Service 
public class UsuarioService {

    
    private final UsuarioRepository usuarioRepository; 
    private final PasswordEncoder passwordEncoder; 
    @Autowired 
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    

   
    @Transactional 
    public Usuario criarUsuario(Usuario usuario) {
        
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        
        return usuarioRepository.save(usuario);
       
    }

    
    @Transactional(readOnly = true) 
    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        
        return usuarioRepository.findById(id);
    }

   
    @Transactional(readOnly = true)
    public List<Usuario> listarTodosUsuarios() {
        
        return usuarioRepository.findAll();
    }

   
    @Transactional
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
       
        return usuarioRepository.findById(id).map(usuarioExistente -> {
            
            usuarioExistente.setNome(usuarioAtualizado.getNome());
            usuarioExistente.setEmail(usuarioAtualizado.getEmail());
            usuarioExistente.setEndereco(usuarioAtualizado.getEndereco());
           
            return usuarioRepository.save(usuarioExistente);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id)); // 
    }

    
    @Transactional
    public void deletarUsuario(Long id) {
        
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com id: " + id);
        }
        
        usuarioRepository.deleteById(id);
    }

    
    @Transactional
    public void trocarSenha(Long id, String novaSenha) {
        
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
       
        usuario.setSenha(passwordEncoder.encode(novaSenha));
        
        usuarioRepository.save(usuario);
    }

    
    @Transactional(readOnly = true)
    public boolean validarLogin(String login, String senha) {
        
        Optional<Usuario> usuarioOpt = usuarioRepository.findByLogin(login);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            
            return passwordEncoder.matches(senha, usuario.getSenha());
        }
        
        return false;
    }

}

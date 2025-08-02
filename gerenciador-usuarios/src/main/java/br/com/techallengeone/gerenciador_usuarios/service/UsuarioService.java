package br.com.techallengeone.gerenciador_usuarios.service;

import br.com.techallengeone.gerenciador_usuarios.model.Usuario;
import br.com.techallengeone.gerenciador_usuarios.model.TipoUsuario;
import br.com.techallengeone.gerenciador_usuarios.repository.UsuarioRepository;
import br.com.techallengeone.gerenciador_usuarios.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,
                     PasswordEncoder passwordEncoder,
                     TipoUsuarioRepository tipoUsuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Transactional
    public Usuario criarUsuario(Usuario usuario, Long tipoUsuarioId) {
        // Verificar se o login já existe
        if (usuarioRepository.findByLogin(usuario.getLogin()).isPresent()) {
            throw new RuntimeException("Login já existe");
        }

        // Verificar se o email já existe
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email já existe");
        }

        // Buscar o tipo de usuário
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(tipoUsuarioId)
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado"));

        // Associar o tipo de usuário
        usuario.setTipoUsuario(tipoUsuario);

        // Criptografar a senha
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
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado, Long tipoUsuarioId) {
        Usuario usuarioExistente = buscarUsuarioPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setEndereco(usuarioAtualizado.getEndereco());

        // Atualizar o tipo de usuário se fornecido
        if (tipoUsuarioId != null) {
            TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(tipoUsuarioId)
                    .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado"));
            usuarioExistente.setTipoUsuario(tipoUsuario);
        }

        return usuarioRepository.save(usuarioExistente);
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





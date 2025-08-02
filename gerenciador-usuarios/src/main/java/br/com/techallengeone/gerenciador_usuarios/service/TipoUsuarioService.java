package br.com.techallengeone.gerenciador_usuarios.service;

import br.com.techallengeone.gerenciador_usuarios.model.TipoUsuario;
import br.com.techallengeone.gerenciador_usuarios.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoUsuarioService {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public TipoUsuarioService(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Transactional
    public TipoUsuario criarTipoUsuario(TipoUsuario tipoUsuario) {
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    public List<TipoUsuario> listarTodosTiposUsuario() {
        return tipoUsuarioRepository.findAll();
    }

    public TipoUsuario buscarTipoUsuarioPorId(Long id) {
        return tipoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado"));
    }

    @Transactional
    public TipoUsuario atualizarTipoUsuario(Long id, TipoUsuario tipoUsuarioAtualizado) {
        TipoUsuario tipoUsuarioExistente = buscarTipoUsuarioPorId(id);
        tipoUsuarioExistente.setNome(tipoUsuarioAtualizado.getNome());
        return tipoUsuarioRepository.save(tipoUsuarioExistente);
    }

    @Transactional
    public void deletarTipoUsuario(Long id) {
        TipoUsuario tipoUsuario = buscarTipoUsuarioPorId(id);
        tipoUsuarioRepository.delete(tipoUsuario);
    }
}

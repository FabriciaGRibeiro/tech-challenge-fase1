package br.com.techallengeone.gerenciador_usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.techallengeone.gerenciador_usuarios.domain.entities.Usuario;
import br.com.techallengeone.gerenciador_usuarios.model.Restaurante;
import br.com.techallengeone.gerenciador_usuarios.repository.RestauranteRepository;
import br.com.techallengeone.gerenciador_usuarios.repository.UsuarioRepository;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public RestauranteService(RestauranteRepository restauranteRepository, UsuarioRepository usuarioRepository) {
        this.restauranteRepository = restauranteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Restaurante criarRestaurante(Restaurante restaurante, Long usuarioId) {
        // Verificar se o usuário existe
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        // Verificar se o usuário é do tipo "Dono de Restaurante"
        if (usuario.getTipoUsuario() == null || 
            !usuario.getTipoUsuario().getNome().equals("Dono de Restaurante")) {
            throw new RuntimeException("Apenas usuários do tipo 'Dono de Restaurante' podem criar restaurantes");
        }
        
        // Verificar se já existe um restaurante com o mesmo nome
        if (restauranteRepository.findByNome(restaurante.getNome()).isPresent()) {
            throw new RuntimeException("Já existe um restaurante com este nome");
        }
        
        restaurante.setUsuario(usuario);
        return restauranteRepository.save(restaurante);
    }

    @Transactional(readOnly = true)
    public List<Restaurante> listarTodosRestaurantes() {
        return restauranteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Restaurante> buscarRestaurantePorId(Long id) {
        return restauranteRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Restaurante> buscarRestaurantesPorUsuario(Long usuarioId) {
        return restauranteRepository.findByUsuarioId(usuarioId);
    }

    @Transactional(readOnly = true)
    public List<Restaurante> buscarRestaurantesPorTipoCozinha(String tipoCozinha) {
        return restauranteRepository.findByTipoCozinha(tipoCozinha);
    }

    @Transactional(readOnly = true)
    public List<Restaurante> buscarRestaurantesPorEndereco(String endereco) {
        return restauranteRepository.findByEnderecoContainingIgnoreCase(endereco);
    }

    @Transactional
    public Restaurante atualizarRestaurante(Long id, Restaurante restauranteAtualizado) {
        Restaurante restauranteExistente = restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
        
        restauranteExistente.setNome(restauranteAtualizado.getNome());
        restauranteExistente.setEndereco(restauranteAtualizado.getEndereco());
        restauranteExistente.setTelefone(restauranteAtualizado.getTelefone());
        restauranteExistente.setTipoCozinha(restauranteAtualizado.getTipoCozinha());
        restauranteExistente.setCapacidade(restauranteAtualizado.getCapacidade());
        
        return restauranteRepository.save(restauranteExistente);
    }

    @Transactional
    public void deletarRestaurante(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
        restauranteRepository.delete(restaurante);
    }
}
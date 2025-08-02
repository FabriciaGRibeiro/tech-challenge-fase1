package br.com.techallengeone.gerenciador_usuarios.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    
    private TipoUsuarioResponseDTO tipoUsuario;
    private Long id;
    private String nome;
    private String email;
    private String login;
    private String endereco;
    private LocalDateTime dataUltimaAlteracao;
}

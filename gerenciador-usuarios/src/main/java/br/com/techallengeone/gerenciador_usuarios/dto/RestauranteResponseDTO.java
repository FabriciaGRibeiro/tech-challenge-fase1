package br.com.techallengeone.gerenciador_usuarios.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RestauranteResponseDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String tipoCozinha;
    private Integer capacidade;
    private LocalDateTime dataUltimaAlteracao;
    private UsuarioResponseDTO usuario;
}
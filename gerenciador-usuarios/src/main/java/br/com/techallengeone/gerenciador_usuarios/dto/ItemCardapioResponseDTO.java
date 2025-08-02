package br.com.techallengeone.gerenciador_usuarios.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ItemCardapioResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String categoria;
    private LocalDateTime dataUltimaAlteracao;
    private RestauranteResponseDTO restaurante;
}
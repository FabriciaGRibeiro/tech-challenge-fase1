package br.com.techallengeone.gerenciador_usuarios.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ItemCardapioRequestDTO {
        
    @NotBlank(message = "O nome do item não pode ficar em branco")
    private String nome;
    
    @NotBlank(message = "A descrição não pode ficar em branco")
    private String descricao;
    
    @NotNull(message = "O preço não pode ser nulo")
    @Positive(message = "O preço deve ser um valor positivo")
    private BigDecimal preco;
    
    @NotBlank(message = "A categoria não pode ficar em branco")
    private String categoria;
    
    @NotNull(message = "O ID do restaurante não pode ser nulo")
    private Long restauranteId;
}
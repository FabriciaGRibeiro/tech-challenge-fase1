package br.com.techallengeone.gerenciador_usuarios.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RestauranteRequestDTO {
    
    @NotBlank(message = "O nome do restaurante não pode ficar em branco")
    private String nome;
    
    @NotBlank(message = "O endereço não pode ficar em branco")
    private String endereco;
    
    @NotBlank(message = "O telefone não pode ficar em branco")
    private String telefone;
    
    @NotBlank(message = "O tipo de cozinha não pode ficar em branco")
    private String tipoCozinha;
    
    @NotNull(message = "A capacidade não pode ser nula")
    @Positive(message = "A capacidade deve ser um número positivo")
    private Integer capacidade;
    
    @NotNull(message = "O ID do usuário não pode ser nulo")
    private Long usuarioId;
}
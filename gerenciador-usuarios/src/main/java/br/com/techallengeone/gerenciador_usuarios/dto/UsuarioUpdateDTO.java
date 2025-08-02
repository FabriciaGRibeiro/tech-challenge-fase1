package br.com.techallengeone.gerenciador_usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioUpdateDTO {
    
    @NotBlank(message = "O nome não pode ficar em branco")
    private String nome;

    @NotBlank(message = "O email não pode ficar em branco")
    @Email(message = "Precisa ser um formato de email válido")
    private String email;

    private String endereco;
}

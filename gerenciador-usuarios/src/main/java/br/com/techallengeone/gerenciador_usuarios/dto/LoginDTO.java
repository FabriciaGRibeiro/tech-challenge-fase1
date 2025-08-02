package br.com.techallengeone.gerenciador_usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "O login não pode ficar em branco")
    private String login;

    @NotBlank(message = "A senha não pode ficar em branco")
    private String senha;
}

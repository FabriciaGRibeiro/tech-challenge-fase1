package br.com.techallengeone.gerenciador_usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TrocaSenhaDTO {
    @NotBlank(message = "A nova senha não pode ficar em branco")
    @Size(min = 6, message = "A senha precisa ter pelo menos 6 caracteres")
    private String novaSenha;
}

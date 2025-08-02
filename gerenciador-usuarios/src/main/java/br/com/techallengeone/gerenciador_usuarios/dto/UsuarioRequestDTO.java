package br.com.techallengeone.gerenciador_usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRequestDTO {
    @NotBlank(message = "O nome não pode ficar em branco")
    private String nome;
    
    @NotBlank(message = "O email não pode ficar em branco")
    @Email(message = "O email deve ser válido")
    private String email;
    
    @NotBlank(message = "O login não pode ficar em branco")
    private String login;
    
    @NotBlank(message = "A senha não pode ficar em branco")
    @Size(min = 6, message = "A senha precisa ter pelo menos 6 caracteres")
    private String senha;
    
    private String endereco;
    
    @NotNull(message = "O tipo de usuário não pode ser nulo")
    private Long tipoUsuarioId;
}

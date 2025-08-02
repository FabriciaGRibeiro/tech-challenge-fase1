// TipoUsuarioRequestDTO.java
package br.com.techallengeone.gerenciador_usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TipoUsuarioRequestDTO {
    @NotBlank(message = "O nome do tipo não pode ficar em branco")
    private String nome;

    
   }

package br.com.techallengeone.gerenciador_usuarios.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column; 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "usuarios") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Usuario {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false) 
    private String nome;

    @Column(nullable = false, unique = true) 
    private String email;

    @Column(nullable = false, unique = true) 
    private String login;

    @Column(nullable = false) 
    private String senha; 

    @Column(name = "data_ultima_alteracao") 
    private LocalDateTime dataUltimaAlteracao;

   
    private String endereco;


    @PrePersist 
    @PreUpdate 
    protected void onUpdate() {
        
        this.dataUltimaAlteracao = LocalDateTime.now();
    }
}

package br.com.techallengeone.gerenciador_usuarios.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

import br.com.techallengeone.gerenciador_usuarios.domain.entities.Usuario;

@Entity
@Table(name = "restaurantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String tipoCozinha;

    @Column(nullable = false)
    private Integer capacidade;

    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario; // Dono do restaurante

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        this.dataUltimaAlteracao = LocalDateTime.now();
    }
}
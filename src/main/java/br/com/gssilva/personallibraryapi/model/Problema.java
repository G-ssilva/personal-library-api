package br.com.gssilva.personallibraryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Problema {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter
    private String titulo;

    @Getter @Setter
    private String descricao;

    @Getter @Setter
    @Column(name = "data_hora")
    @CreationTimestamp
    private LocalDateTime dataHora;

    @Getter @Setter
    private String solucao;

    @Getter @Setter
    @JoinColumn(name = "usuario_id")
    @ManyToOne
    private Usuario usuarioId;
}

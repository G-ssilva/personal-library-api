package br.com.gssilva.personallibraryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Problema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private String titulo;

    @Getter @Setter
    private String descricao;

    @Getter @Setter
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Getter @Setter
    private String solucao;

    @Getter @Setter
    @JoinColumn(name = "usuario_id")
    @ManyToOne
    private Usuario usuarioId;
}

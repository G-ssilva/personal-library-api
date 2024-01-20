package br.com.gssilva.personallibraryapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @NotNull
    private String titulo;

    @Getter @Setter
    @NotNull
    private String descricao;

    @Getter @Setter
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Getter @Setter
    private String solucao;

    @Getter @Setter
    @NotNull
    @JoinColumn(name = "usuario_id")
    @ManyToOne
    private Usuario usuarioId;
}

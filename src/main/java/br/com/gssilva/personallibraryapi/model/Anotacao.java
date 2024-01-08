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
public class Anotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter
    private String titulo;

    @Getter @Setter
    private String descricao;

    @Getter @Setter
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Getter @Setter
    @JoinColumn(name = "livro_id")
    @ManyToOne
    private Livro livroId;
}

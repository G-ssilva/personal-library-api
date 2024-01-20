package br.com.gssilva.personallibraryapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter
    @NotNull
    private String titulo;

    @Getter @Setter
    @NotNull
    private String autor;

    @Getter @Setter
    @NotNull
    @Column(name = "numero_paginas")
    private long numeroPaginas;

    @Getter @Setter
    @NotNull
    private String idioma;

    @Getter @Setter
    @NotNull
    private String editora;

    @Getter @Setter
    @NotNull
    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @Getter @Setter
    @NotNull
    @JoinColumn(name = "usuario_id")
    @ManyToOne
    private Usuario usuarioId;

}

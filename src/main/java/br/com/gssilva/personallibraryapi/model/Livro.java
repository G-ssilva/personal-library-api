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
public class Livro {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter
    private String titulo;

    @Getter @Setter
    private String autor;

    @Getter @Setter
    @Column(name = "numero_paginas")
    private long numeroPaginas;

    @Getter @Setter
    private String idioma;

    @Getter @Setter
    private String editora;

    @Getter @Setter
    @Column(name = "data_publicacao")
    private LocalDateTime dataPublicacao;

    @Getter @Setter
    @JoinColumn(name = "usuario_id")
    @ManyToOne
    private Usuario usuarioId;

}

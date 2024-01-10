package br.com.gssilva.personallibraryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter
    @Column(unique = true)
    private String login;

    @Getter @Setter
    private String senha;

    @Getter @Setter
    @JoinColumn(name = "perfil_id")
    @ManyToOne
    private Perfil perfilId;

}

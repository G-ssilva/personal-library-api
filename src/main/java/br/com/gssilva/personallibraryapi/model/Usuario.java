package br.com.gssilva.personallibraryapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Column(unique = true)
    private String login;

    @Getter @Setter
    @NotNull
    private String senha;

    @Getter @Setter
    @NotNull
    @JoinColumn(name = "perfil_id")
    @ManyToOne
    private Perfil perfilId;

}

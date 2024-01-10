package br.com.gssilva.personallibraryapi.repository;

import br.com.gssilva.personallibraryapi.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}

package br.com.gssilva.personallibraryapi.repository;

import br.com.gssilva.personallibraryapi.model.Problema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemaRepository extends JpaRepository<Problema, Long> {
}

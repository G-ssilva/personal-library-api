package br.com.gssilva.personallibraryapi.repository;

import br.com.gssilva.personallibraryapi.model.Anotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {
}

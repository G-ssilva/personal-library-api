package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.model.Anotacao;
import br.com.gssilva.personallibraryapi.model.Livro;
import br.com.gssilva.personallibraryapi.repository.AnotacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log
public class AnotacaoService {

    @Autowired
    private AnotacaoRepository anotacaoRepository;

    @Autowired
    private LivroService livroService;

    public void vincularLivroSeExiste(Anotacao anotacao, long livroId) {
        Livro livroRetornado = livroService.listarPorId(livroId);

        log.info("Livro encontrado. Estarei vinculando na Anotação");
        anotacao.setLivroId(livroRetornado);
    }

    public void persistir(Anotacao anotacao) {
        log.info("Persistindo o objeto Anotação na base de dados");
        anotacaoRepository.save(anotacao);
    }

    public Anotacao listarPorId(long id) {
        Optional<Anotacao> anotacao = anotacaoRepository.findById(id);

        if (anotacao.isEmpty()) {
            throw new EntityNotFoundException("ID da anotação não encontrada na base de dados");
        }

        log.info("Anotação encontrada na base de dados. Irei retornar");
        return anotacao.get();
    }

    public Anotacao anotacaoReferencia(long id) {
        log.info("Buscando na base de dados a referência do ID da Anotação informado");
        return anotacaoRepository.getReferenceById(id);
    }

    public void alterarLivroSeInformado(Anotacao anotacao, Long livroId) {
        if (livroId != null) {
            log.info("ID do livro informado para alteração na Anotação. Irei verificar se o livro existe na base de dados");
            vincularLivroSeExiste(anotacao, livroId);
        }
    }

    public void deletarPorId(long id) {
        log.info("Verificando se o ID da Anotação informado para exclusão existe na base de dados");
        listarPorId(id);

        anotacaoRepository.deleteById(id);
        log.info("ID da Anotação deletado da base de dados");
    }
}

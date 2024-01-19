package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.model.Anotacao;
import br.com.gssilva.personallibraryapi.model.Livro;
import br.com.gssilva.personallibraryapi.repository.AnotacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnotacaoService {

    @Autowired
    private AnotacaoRepository anotacaoRepository;

    @Autowired
    private LivroService livroService;

    public void vincularLivroSeExiste(Anotacao anotacao, long livroId) {
        Livro livroRetornado = livroService.listarPorId(livroId);

        anotacao.setLivroId(livroRetornado);
    }

    public void persistir(Anotacao anotacao) {
        anotacaoRepository.save(anotacao);
    }

    public Anotacao listarPorId(long id) {
        Optional<Anotacao> anotacao = anotacaoRepository.findById(id);

        if (anotacao.isEmpty()) {
            throw new EntityNotFoundException("ID da anotação não encontrada na base de dados");
        }

        return anotacao.get();
    }

    public Anotacao anotacaoReferencia(long id) {
        return anotacaoRepository.getReferenceById(id);
    }

    public void alterarLivroSeInformado(Anotacao anotacao, Long livroId) {
        if (livroId != null) {
            vincularLivroSeExiste(anotacao, livroId);
        }
    }

    public void deletarPorId(long id) {
        listarPorId(id);

        anotacaoRepository.deleteById(id);
    }
}

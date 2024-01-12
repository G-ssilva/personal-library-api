package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.model.Anotacao;
import br.com.gssilva.personallibraryapi.model.Livro;
import br.com.gssilva.personallibraryapi.repository.AnotacaoRepository;
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

    public void cadastrar(Anotacao anotacao) {
        try {
            anotacaoRepository.save(anotacao);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar anotação");
        }
    }

    public Anotacao listarPorId(long id) {
        Optional<Anotacao> anotacao = anotacaoRepository.findById(id);

        if(anotacao.isEmpty()){
            throw new RuntimeException("Id da anotação não existe na base de dados");
        }

        return anotacao.get();
    }

    public void deletarPorId(long id) {
        listarPorId(id);

        try {
            anotacaoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar anotação");
        }
    }
}

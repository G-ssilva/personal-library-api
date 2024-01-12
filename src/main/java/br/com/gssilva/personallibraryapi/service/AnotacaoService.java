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
        Optional<Livro> livroRetornado = livroService.retornarLivroSeExiste(livroId);

        if(livroRetornado.isEmpty()){
            throw new RuntimeException("Usuário não encontrado na base de dados");
        }

        anotacao.setLivroId(livroRetornado.get());
    }

    public void cadastrar(Anotacao anotacao) {
        try {
            anotacaoRepository.save(anotacao);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar anotação");
        }
    }

    public Optional<Anotacao> listarPorId(long id) {
        return anotacaoRepository.findById(id);
    }
}

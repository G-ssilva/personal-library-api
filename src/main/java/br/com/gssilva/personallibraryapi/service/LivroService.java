package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.model.Livro;
import br.com.gssilva.personallibraryapi.model.Usuario;
import br.com.gssilva.personallibraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroRepository livroRepository;

    public void vincularUsuarioSeExiste(Livro livro, Long usuarioId) {
        Usuario usuarioRetornado = usuarioService.listarPorId(usuarioId);

        livro.setUsuarioId(usuarioRetornado);
    }

    public void cadastrar(Livro livro) {
        try {
            livroRepository.save(livro);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar livro");
        }
    }

    public Livro listarPorId(long id) {
        Optional<Livro> livro = livroRepository.findById(id);

        if (livro.isEmpty()) {
            throw new RuntimeException("Id do livro não existe na base de dados");
        }

        return livro.get();
    }

    public Livro livroReferencia(long id) {
        try {
            return livroRepository.getReferenceById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar referência do id do livro informado");
        }
    }

    public void alterarUsuarioSeInformado(Livro livro, Long usuarioId) {
        if(usuarioId != null){
            vincularUsuarioSeExiste(livro, usuarioId);
        }
    }

    public void deletarPorId(long id) {
        listarPorId(id);

        try {
            livroRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar livro");
        }
    }

}

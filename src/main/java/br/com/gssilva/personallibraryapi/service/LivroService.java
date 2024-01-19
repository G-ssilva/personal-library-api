package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.model.Livro;
import br.com.gssilva.personallibraryapi.model.Usuario;
import br.com.gssilva.personallibraryapi.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public void persistir(Livro livro) {
        livroRepository.save(livro);
    }

    public Livro listarPorId(long id) {
        Optional<Livro> livro = livroRepository.findById(id);

        if (livro.isEmpty()) {
            throw new EntityNotFoundException("ID do livro não encontrado na base de dados");
        }

        return livro.get();
    }

    public Livro livroReferencia(long id) {
        return livroRepository.getReferenceById(id);
    }

    public void alterarUsuarioSeInformado(Livro livro, Long usuarioId) {
        if (usuarioId != null) {
            vincularUsuarioSeExiste(livro, usuarioId);
        }
    }

    public void deletarPorId(long id) {
        listarPorId(id);

        livroRepository.deleteById(id);
    }
}

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
        Optional<Usuario> usuarioRetornado = usuarioService.retornarUsuarioSeExiste(usuarioId);

        if(usuarioRetornado.isEmpty()){
            throw new RuntimeException("Usuário não encontrado na base de dados");
        }

        livro.setUsuarioId(usuarioRetornado.get());
    }

    public void cadastrar(Livro livro) {
        try {
            livroRepository.save(livro);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar problema");
        }
    }

    public Optional<Livro> listarPorId(long id) {
        return livroRepository.findById(id);
    }

    public Optional<Livro> retornarLivroSeExiste(long livroId) {
        return livroRepository.findById(livroId);
    }
}

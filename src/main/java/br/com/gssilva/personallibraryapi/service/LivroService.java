package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.dto.livro.LivroDto;
import br.com.gssilva.personallibraryapi.model.Livro;
import br.com.gssilva.personallibraryapi.model.Usuario;
import br.com.gssilva.personallibraryapi.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class LivroService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroRepository livroRepository;

    public void vincularUsuarioSeExiste(Livro livro, Long usuarioId) {
        Usuario usuarioRetornado = usuarioService.listarPorId(usuarioId);

        log.info("Usuário encontrado. Estarei vinculando no Livro");
        livro.setUsuarioId(usuarioRetornado);
    }

    public void persistir(Livro livro) {
        log.info("Persistindo o objeto Livro na base de dados");
        livroRepository.save(livro);
    }

    public Livro listarPorId(long id) {
        Optional<Livro> livro = livroRepository.findById(id);

        if (livro.isEmpty()) {
            throw new EntityNotFoundException("ID do livro não encontrado na base de dados");
        }

        log.info("Livro encontrado na base de dados. Irei retornar");
        return livro.get();
    }

    public List<Livro> listarTodos() {
        log.info("Buscando todos os livros da base de dados");
        return livroRepository.findAll();
    }

    public Livro livroReferencia(long id) {
        log.info("Buscando na base de dados a referência do ID do Livro informado");
        return livroRepository.getReferenceById(id);
    }

    public void alterarUsuarioSeInformado(Livro livro, Long usuarioId) {
        if (usuarioId != null) {
            log.info("ID do usuário informado para alteração no Livro. Irei verificar se o usuário existe na base de dados");
            vincularUsuarioSeExiste(livro, usuarioId);
        }
    }

    public void deletarPorId(long id) {
        log.info("Verificando se o ID do Livro informado para exclusão existe na base de dados");
        listarPorId(id);

        livroRepository.deleteById(id);
        log.info("ID do Livro deletado da base de dados");
    }


    public List<LivroDto> retornarListaLivros() {
        List<Livro> livros = listarTodos();
        List<LivroDto> livrosDto = new ArrayList<>();

        livros.forEach(livro -> livrosDto.add(new LivroDto(livro)));

        log.info("Encontrei " + livrosDto.size() + " livros na base de dados. Irei retornar");
        return livrosDto;
    }
}

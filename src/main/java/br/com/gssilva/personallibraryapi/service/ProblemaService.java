package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.dto.problema.ProblemaDto;
import br.com.gssilva.personallibraryapi.model.Problema;
import br.com.gssilva.personallibraryapi.model.Usuario;
import br.com.gssilva.personallibraryapi.repository.ProblemaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class ProblemaService {

    @Autowired
    private ProblemaRepository problemaRepository;

    @Autowired
    private UsuarioService usuarioService;

    public void vincularUsuarioSeExiste(Problema problema, Long usuarioId) {
        Usuario usuarioRetornado = usuarioService.listarPorId(usuarioId);

        log.info("Usuário encontrado. Estarei vinculando no Problema");
        problema.setUsuarioId(usuarioRetornado);
    }

    public void persistir(Problema problema) {
        log.info("Persistindo o objeto Problema na base de dados");
        problemaRepository.save(problema);
    }

    public Problema listarPorId(long id) {
        Optional<Problema> problema = problemaRepository.findById(id);

        if (problema.isEmpty()) {
            throw new EntityNotFoundException("ID do problema não encontrado na base de dados");
        }

        log.info("Objeto Problema encontrado na base de dados. Irei retornar");
        return problema.get();
    }

    public List<Problema> listarTodos() {
        log.info("Buscando todos os problemas da base de dados");
        return problemaRepository.findAll();
    }

    public Problema problemaReferencia(long id) {
        log.info("Buscando na base de dados a referência do ID do Problema informado");
        return problemaRepository.getReferenceById(id);
    }

    public void alterarUsuarioSeInformado(Problema problema, Long usuarioId) {
        if (usuarioId != null) {
            log.info("ID do usuário informado para alteração no Problema. Irei verificar se o usuário existe na base de dados");
            vincularUsuarioSeExiste(problema, usuarioId);
        }
    }

    public void deletarPorId(long id) {
        log.info("Verificando se o ID do Problema informado para exclusão existe na base de dados");
        listarPorId(id);

        problemaRepository.deleteById(id);
        log.info("ID do Problema deletado da base de dados");
    }

    public List<ProblemaDto> retornarListaProblemas() {
        List<Problema> problemas = listarTodos();
        List<ProblemaDto> problemasDto = new ArrayList<>();

        problemas.forEach(problema -> problemasDto.add(new ProblemaDto(problema)));

        log.info("Encontrei " + problemasDto.size() + " problemas na base de dados. Irei retornar");
        return problemasDto;
    }
}

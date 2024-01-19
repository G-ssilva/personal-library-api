package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.model.Problema;
import br.com.gssilva.personallibraryapi.model.Usuario;
import br.com.gssilva.personallibraryapi.repository.ProblemaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProblemaService {

    @Autowired
    private ProblemaRepository problemaRepository;

    @Autowired
    private UsuarioService usuarioService;

    public void vincularUsuarioSeExiste(Problema problema, Long usuarioId) {
        Usuario usuarioRetornado = usuarioService.listarPorId(usuarioId);

        problema.setUsuarioId(usuarioRetornado);
    }

    public void persistir(Problema problema) {
        problemaRepository.save(problema);
    }

    public Problema listarPorId(long id) {
        Optional<Problema> problema = problemaRepository.findById(id);

        if (problema.isEmpty()) {
            throw new EntityNotFoundException("ID do problema não encontrado na base de dados");
        }

        return problema.get();
    }

    public Problema problemaReferencia(long id) {
        return problemaRepository.getReferenceById(id);
    }

    public void alterarUsuarioSeInformado(Problema problema, Long usuarioId) {
        if (usuarioId != null) {
            vincularUsuarioSeExiste(problema, usuarioId);
        }
    }

    public void deletarPorId(long id) {
        listarPorId(id);

        problemaRepository.deleteById(id);
    }
}

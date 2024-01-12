package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.model.Problema;
import br.com.gssilva.personallibraryapi.model.Usuario;
import br.com.gssilva.personallibraryapi.repository.ProblemaRepository;
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

    public void cadastrar(Problema problema) {
        try {
            problemaRepository.save(problema);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar problema");
        }
    }

    public Problema listarPorId(long id) {
        Optional<Problema> problema = problemaRepository.findById(id);

        if(problema.isEmpty()){
            throw new RuntimeException("Id do problema não existe na base de dados");
        }

        return problema.get();
    }

    public void deletarPorId(long id) {
        listarPorId(id);

        try {
            problemaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar problema");
        }
    }
}

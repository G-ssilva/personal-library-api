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
        Optional<Usuario> usuarioRetornado = usuarioService.listarPorId(usuarioId);

        if(usuarioRetornado.isEmpty()){
            throw new RuntimeException("Usuário não encontrado na base de dados");
        }

        problema.setUsuarioId(usuarioRetornado.get());
    }

    public void cadastrar(Problema problema) {
        try {
            problemaRepository.save(problema);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar problema");
        }
    }

    public Optional<Problema> listarPorId(long id) {
        return problemaRepository.findById(id);
    }
}

package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.model.Perfil;
import br.com.gssilva.personallibraryapi.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public void cadastrar(Perfil perfil) {
        try {
            perfilRepository.save(perfil);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar perfil");
        }
    }

    public Perfil listarPorId(long id) {
        Optional<Perfil> perfil = perfilRepository.findById(id);

        if(perfil.isEmpty()){
            throw new RuntimeException("Id do perfil não existe na base de dados");
        }

        return perfil.get();
    }

    public void deletarPorId(long id) {
        listarPorId(id);

        try {
            perfilRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar perfil");
        }
    }
}

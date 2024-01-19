package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.model.Perfil;
import br.com.gssilva.personallibraryapi.repository.PerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public void persistir(Perfil perfil) {
        perfilRepository.save(perfil);
    }

    public Perfil listarPorId(long id) {
        Optional<Perfil> perfil = perfilRepository.findById(id);

        if (perfil.isEmpty()) {
            throw new EntityNotFoundException("ID do perfil não encontrado na base de dados");
        }

        return perfil.get();
    }

    public Perfil perfilReferencia(long id) {
        return perfilRepository.getReferenceById(id);
    }

    public void deletarPorId(long id) {
        listarPorId(id);

        perfilRepository.deleteById(id);
    }
}

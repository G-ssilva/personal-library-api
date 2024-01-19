package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.dto.perfil.PerfilDto;
import br.com.gssilva.personallibraryapi.model.Perfil;
import br.com.gssilva.personallibraryapi.repository.PerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public void persistir(Perfil perfil) {
        log.info("Persistindo o objeto Perfil na base de dados");
        perfilRepository.save(perfil);
    }

    public Perfil listarPorId(long id) {
        Optional<Perfil> perfil = perfilRepository.findById(id);

        if (perfil.isEmpty()) {
            throw new EntityNotFoundException("ID do perfil não encontrado na base de dados");
        }

        log.info("Perfil encontrado na base de dados. Irei retornar");
        return perfil.get();
    }

    public List<Perfil> listarTodos() {
        log.info("Buscando todos os perfis da base de dados");
        return perfilRepository.findAll();
    }

    public Perfil perfilReferencia(long id) {
        log.info("Buscando na base de dados a referência do ID do Perfil informado");
        return perfilRepository.getReferenceById(id);
    }

    public void deletarPorId(long id) {
        log.info("Verificando se o ID do Perfil informado para exclusão existe na base de dados");
        listarPorId(id);

        perfilRepository.deleteById(id);
        log.info("ID do Perfil deletado da base de dados");
    }

    public List<PerfilDto> retornarListaPerfis() {
        List<Perfil> perfis = listarTodos();
        List<PerfilDto> livrosDto = new ArrayList<>();

        perfis.forEach(perfil -> livrosDto.add(new PerfilDto(perfil)));

        log.info("Encontrei " + livrosDto.size() + " livros na base de dados. Irei retornar");
        return livrosDto;
    }
}

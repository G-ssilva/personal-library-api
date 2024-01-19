package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.model.Perfil;
import br.com.gssilva.personallibraryapi.model.Usuario;
import br.com.gssilva.personallibraryapi.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilService perfilService;

    public void vincularPerfilSeExiste(Usuario usuario, long perfilId) {
        Perfil perfilRetornado = perfilService.listarPorId(perfilId);

        usuario.setPerfilId(perfilRetornado);
    }

    public void persistir(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario listarPorId(long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            throw new EntityNotFoundException("ID do usuário não encontrado na base de dados");
        }

        return usuario.get();
    }

    public Usuario usuarioReferencia(long id) {
        return usuarioRepository.getReferenceById(id);
    }

    public void alterarPerfilSeInformado(Usuario usuario, Long perfilId) {
        if (perfilId != null) {
            vincularPerfilSeExiste(usuario, perfilId);
        }
    }

    public void deletarPorId(long id) {
        listarPorId(id);

        usuarioRepository.deleteById(id);
    }
}

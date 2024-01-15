package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.model.Perfil;
import br.com.gssilva.personallibraryapi.model.Usuario;
import br.com.gssilva.personallibraryapi.repository.UsuarioRepository;
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

    public void cadastrar(Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar usuário");
        }
    }

    public Usuario listarPorId(long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario.isEmpty()){
            throw new RuntimeException("Id do usuário não existe na base de dados");
        }

        return usuario.get();
    }

    public Usuario usuarioReferencia(long id) {
        try {
            return usuarioRepository.getReferenceById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar referência do id do usuário informado");
        }
    }

    public void alterarPerfilSeInformado(Usuario usuario, Long perfilId) {
        if(perfilId != null){
            vincularPerfilSeExiste(usuario, perfilId);
        }
    }

    public void deletarPorId(long id) {
        listarPorId(id);

        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar usuário");
        }
    }
}

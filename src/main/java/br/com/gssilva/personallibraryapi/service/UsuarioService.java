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
        Optional<Perfil> perfilRetornado = perfilService.listarPorId(perfilId);

        if(perfilRetornado.isEmpty()){
            throw new RuntimeException("Perfil não encontrado na base de dados");
        }

        usuario.setPerfilId(perfilRetornado.get());
    }

    public void cadastrar(Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar usuário");
        }
    }

    public Optional<Usuario> listarPorId(long id) {
        return usuarioRepository.findById(id);
    }

}

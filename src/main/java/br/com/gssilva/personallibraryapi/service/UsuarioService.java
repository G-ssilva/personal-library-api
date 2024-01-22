package br.com.gssilva.personallibraryapi.service;

import br.com.gssilva.personallibraryapi.dto.usuario.UsuarioDto;
import br.com.gssilva.personallibraryapi.model.Perfil;
import br.com.gssilva.personallibraryapi.model.Usuario;
import br.com.gssilva.personallibraryapi.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilService perfilService;

    public void vincularPerfilSeExiste(Usuario usuario, long perfilId) {
        Perfil perfilRetornado = perfilService.listarPorId(perfilId);

        log.info("Perfil encontrado. Estarei vinculando no Usuário");
        usuario.setPerfilId(perfilRetornado);
    }

    public void persistir(Usuario usuario) {
        log.info("Persistindo o objeto Usuário na base de dados");
        usuarioRepository.save(usuario);
    }

    public Usuario listarPorId(long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            throw new EntityNotFoundException("ID do usuário não encontrado na base de dados");
        }

        log.info("Usuário encontrada na base de dados. Irei retornar");
        return usuario.get();
    }

    public List<Usuario> listarTodos() {
        log.info("Buscando todos os usuários da base de dados");
        return usuarioRepository.findAll();
    }

    public Usuario usuarioReferencia(long id) {
        log.info("Buscando na base de dados a referência do ID do Usuário informado");
        return usuarioRepository.getReferenceById(id);
    }

    public void alterarPerfilSeInformado(Usuario usuario, Long perfilId) {
        if (perfilId != null) {
            log.info("ID do perfil informado para alteração no Usuário. Irei verificar se o perfil existe na base de dados");
            vincularPerfilSeExiste(usuario, perfilId);
        }
    }

    public void deletarPorId(long id) {
        log.info("Verificando se o ID do Usuário informado para exclusão existe na base de dados");
        listarPorId(id);

        usuarioRepository.deleteById(id);
        log.info("ID do Usuário deletado da base de dados");
    }

    public List<UsuarioDto> retornarListaUsuarios() {
        List<Usuario> usuarios = listarTodos();
        List<UsuarioDto> usuariosDto = new ArrayList<>();

        usuarios.forEach(usuario -> usuariosDto.add(new UsuarioDto(usuario)));

        log.info("Encontrei " + usuariosDto.size() + " usuários na base de dados. Irei retornar");
        return usuariosDto;
    }

    public void criptografarSenha(Usuario usuario) {
        log.info("Criptogranfo senha do usuário");
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
    }

    public UserDetails findByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }
}

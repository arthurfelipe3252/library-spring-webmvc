package br.com.bpkedu.library_spring_webmvc.service;

import br.com.bpkedu.library_spring_webmvc.domain.Usuario;
import br.com.bpkedu.library_spring_webmvc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(usuarioAtualizado.getNome());
                    usuario.setEmail(usuarioAtualizado.getEmail());
                    usuario.setEndereco(usuarioAtualizado.getEndereco());
                    usuario.setTelefone(usuarioAtualizado.getTelefone());
                    usuario.setSenha(usuarioAtualizado.getSenha());
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}

package br.com.bpkedu.library_spring_webmvc.service;

import br.com.bpkedu.library_spring_webmvc.domain.*;
import br.com.bpkedu.library_spring_webmvc.repository.EmprestimoRepository;
import br.com.bpkedu.library_spring_webmvc.repository.LivroRepository;
import br.com.bpkedu.library_spring_webmvc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Emprestimo criarEmprestimo(Long usuarioId, List<Long> livroIds, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<EmprestimoItem> itens = new ArrayList<>();
        Emprestimo emprestimo = new Emprestimo(dataEmprestimo, dataDevolucao, usuario);

        for (Long livroId : livroIds) {
            Livro livro = livroRepository.findById(livroId)
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado: " + livroId));

            boolean emprestado = emprestimoRepository.findEmprestimoAtivoByLivro(livro, LocalDate.now()).isPresent();
            if (emprestado) {
                throw new RuntimeException("Livro já emprestado: " + livro.getTitulo());
            }

            itens.add(new EmprestimoItem(livro, emprestimo));
        }

        emprestimo.setItens(itens);
        return emprestimoRepository.save(emprestimo);
    }

    public Optional<Emprestimo> buscarEmprestimoPorId(Long id) {
        return emprestimoRepository.findById(id);
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public void deletar(Long id) {
        emprestimoRepository.deleteById(id);
    }

    public Optional<Emprestimo> verificarLivroEmprestado(Long livroId) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return emprestimoRepository.findEmprestimoAtivoByLivro(livro, LocalDate.now());
    }
}

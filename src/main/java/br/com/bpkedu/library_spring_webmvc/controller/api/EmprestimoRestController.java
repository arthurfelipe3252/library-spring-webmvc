package br.com.bpkedu.library_spring_webmvc.controller.api;

import br.com.bpkedu.library_spring_webmvc.domain.Emprestimo;
import br.com.bpkedu.library_spring_webmvc.dto.EmprestimoRequest;
import br.com.bpkedu.library_spring_webmvc.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/emprestimos")
public class EmprestimoRestController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<?> criarEmprestimo(@RequestBody EmprestimoRequest request) {
        try {
            Emprestimo emprestimo = emprestimoService.criarEmprestimo(
                    request.getUsuarioId(),
                    request.getLivroIds(),
                    request.getDataEmprestimo(),
                    request.getDataDevolucao()
            );
            return ResponseEntity.ok(emprestimo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        return emprestimoService.buscarEmprestimoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Emprestimo> listar() {
        return emprestimoService.listarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        emprestimoService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/livro/{livroId}/verifica")
    public ResponseEntity<?> verificarLivroEmprestado(@PathVariable Long livroId) {
        return emprestimoService.verificarLivroEmprestado(livroId)
                .map(emprestimo -> Map.of(
                        "emprestado", true,
                        "emprestimoId", emprestimo.getId(),
                        "dataEmprestimo", emprestimo.getDataEmprestimo(),
                        "dataDevolucao", emprestimo.getDataDevolucao(),
                        "usuario", Map.of(
                                "id", emprestimo.getUsuario().getId(),
                                "nome", emprestimo.getUsuario().getNome()
                        )
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.ok(Map.of("emprestado", false)));
    }
}

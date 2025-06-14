package br.com.bpkedu.library_spring_webmvc.controller.api;

import br.com.bpkedu.library_spring_webmvc.domain.Livro;
import br.com.bpkedu.library_spring_webmvc.dto.LivroDTO;
import br.com.bpkedu.library_spring_webmvc.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/livros")
public class LivroRestController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> livros = livroService.listarTodos();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> detalharLivro(@PathVariable Long id) {
        Livro livro = livroService.buscarPorId(id);
        if (livro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(livro);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        Livro existente = livroService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        livro.setId(id);
        Livro atualizado = livroService.salvar(livro);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        Livro existente = livroService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public List<Livro> getAll() {
        return livroService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Livro> salvarLivro(@RequestBody Livro livro) {
        Livro salvo = livroService.salvar(livro);
        return ResponseEntity.status(201).body(salvo);
    }


}

package br.com.bpkedu.library_spring_webmvc.repository;

import br.com.bpkedu.library_spring_webmvc.domain.Emprestimo;
import br.com.bpkedu.library_spring_webmvc.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query("SELECT ei.emprestimo FROM EmprestimoItem ei WHERE ei.livro = :livro AND ei.emprestimo.dataDevolucao > :hoje")
    Optional<Emprestimo> findEmprestimoAtivoByLivro(Livro livro, LocalDate hoje);
}

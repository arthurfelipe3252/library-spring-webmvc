package br.com.bpkedu.library_spring_webmvc.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dataemprestimo")
    private LocalDate dataEmprestimo;

    @Column(name = "datadevolucao")
    private LocalDate dataDevolucao;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<EmprestimoItem> itens;

    public Emprestimo() {}

    public Emprestimo(LocalDate dataEmprestimo, LocalDate dataDevolucao, Usuario usuario) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<EmprestimoItem> getItens() {
        return itens;
    }

    public void setItens(List<EmprestimoItem> itens) {
        this.itens = itens;
    }
}


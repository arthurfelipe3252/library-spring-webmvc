package br.com.bpkedu.library_spring_webmvc.dto;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoRequest {
    private Long usuarioId;
    private List<Long> livroIds;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<Long> getLivroIds() {
        return livroIds;
    }

    public void setLivroIds(List<Long> livroIds) {
        this.livroIds = livroIds;
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
}

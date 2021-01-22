package br.com.orangetalents.forum.controller.dto;

import br.com.orangetalents.forum.model.Answer;

import java.time.LocalDateTime;

public class AnswerDTO {

    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public AnswerDTO(Answer answer) {
        this.id = answer.getId();
        this.mensagem = answer.getMessage();
        this.dataCriacao = answer.getCreationDate();
        this.nomeAutor = answer.getAuthor().getName();
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
}

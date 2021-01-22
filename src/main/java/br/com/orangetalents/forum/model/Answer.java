package br.com.orangetalents.forum.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    @ManyToOne
    private Topic topic;
    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToOne
    private Usuario author;
    private Boolean solution = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Usuario getAuthor() {
        return author;
    }

    public void setAuthor(Usuario author) {
        this.author = author;
    }

    public Boolean getSolution() {
        return solution;
    }

    public void setSolution(Boolean solution) {
        this.solution = solution;
    }
}

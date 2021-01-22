package br.com.orangetalents.forum.controller.dto;


import br.com.orangetalents.forum.model.Topic;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class TopicDTO {


    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate = LocalDateTime.now();

    public TopicDTO(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.creationDate = topic.getCreationDate();
    }

    public static Page<TopicDTO> convertToDTO(Page<Topic> topics) {
        return topics.map(TopicDTO::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}

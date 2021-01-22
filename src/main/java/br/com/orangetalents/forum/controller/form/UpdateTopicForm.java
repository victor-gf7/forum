package br.com.orangetalents.forum.controller.form;

import br.com.orangetalents.forum.model.Topic;
import br.com.orangetalents.forum.repository.TopicRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateTopicForm {
    
    
    @NotBlank @Size(min = 5)
    private String title;
    @NotBlank @Size(min = 10)
    private String message;

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

    public Topic toUpdate(Long id, TopicRepository topicRepository) {
        Topic topic = topicRepository.getOne(id);
        topic.setTitle(this.title);
        topic.setMessage(this.message);

        return topic;
    }
}

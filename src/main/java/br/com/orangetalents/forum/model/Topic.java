package br.com.orangetalents.forum.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Topic {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private TopicStatus status = TopicStatus.NOT_ANSWERED;

    @ManyToOne
    private Usuario author;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "topic")
    private List<Answer> answers = new ArrayList<>();

    @Deprecated
    public Topic() {
    }

    public Topic(String title, String message, Course course) {
        this.title = title;
        this.message = message;
        this.course = course;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Topic topic = (Topic) obj;
        if(id == null){
            return topic.id == null;
        } else return id.equals(topic.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
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

    public TopicStatus getStatus() {
        return status;
    }

    public void setStatus(TopicStatus status) {
        this.status = status;
    }

    public Usuario getAuthor() {
        return author;
    }

    public void setAuthor(Usuario author) {
        this.author = author;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}

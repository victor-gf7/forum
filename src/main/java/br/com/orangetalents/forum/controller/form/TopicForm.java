package br.com.orangetalents.forum.controller.form;


import br.com.orangetalents.forum.model.Course;
import br.com.orangetalents.forum.model.Topic;
import br.com.orangetalents.forum.repository.CourseRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TopicForm {

    @NotBlank @Size(min = 5)
    private String title;
    @NotBlank @Size(min = 10)
    private String message;
    @NotBlank
    private String courseName;

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Topic convertToEntity(CourseRepository courseRepository) {
        Course course = courseRepository.findByName(courseName);
        return new Topic(title, message, course);
    }
}

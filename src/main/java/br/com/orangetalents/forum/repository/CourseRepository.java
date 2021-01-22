package br.com.orangetalents.forum.repository;

import br.com.orangetalents.forum.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByName(String name);
}

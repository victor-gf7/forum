package br.com.orangetalents.forum.repository;

import br.com.orangetalents.forum.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Page<Topic> findByCourse_name(String courseName, Pageable pagination);
}

package br.com.orangetalents.forum.controller;

import br.com.orangetalents.forum.controller.dto.TopicsDetailDTO;
import br.com.orangetalents.forum.controller.form.TopicForm;
import br.com.orangetalents.forum.controller.form.UpdateTopicForm;
import br.com.orangetalents.forum.repository.CourseRepository;
import br.com.orangetalents.forum.repository.TopicRepository;
import br.com.orangetalents.forum.controller.dto.TopicDTO;
import br.com.orangetalents.forum.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    /*
        The following attributes are required for pagination:
        page -> Which represents which page the customer wants;
        size -> Representing the number of records per page

        optional:
        sort -> Which is the orientation (ascending or descending) and by which attribute to sort

        Ex:
        http://localhost:8080/topics?page=01&size=5
        http://localhost:8080/topi?page=0&size=5&sort=id,desc&sort=creationDate,asc
     */
    @GetMapping
    @Cacheable(value = "topicsList")
    public Page<TopicDTO> toList(@RequestParam(required = false) String courseName,
                               @PageableDefault(page = 0, size = 5, direction = Sort.Direction.ASC, sort = "creationDate") Pageable pagination){
        Page<Topic> topics;
        if(courseName == null){
            topics = topicRepository.findAll(pagination);
        } else{
            topics = topicRepository.findByCourse_name(courseName, pagination);
        }
        return TopicDTO.convertToDTO(topics);
    }


    /*
        When registering a new resource, you must return 201 and the URI to access it
     */
    @PostMapping
    @Transactional
    @CacheEvict(value = "topicsList", allEntries = true)
    public ResponseEntity<TopicDTO> toRegister(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder){
        Topic topic = form.convertToEntity(courseRepository);
        topicRepository.save(topic);

        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicDTO(topic));
    }

    /*
        The return can be replaced by:
        if(topic.isPresent()){
            return ResponseEntity.ok(new TopicDTO(topic.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
     */

    @GetMapping(value = "/{id}")
    public ResponseEntity<TopicsDetailDTO> toDetail(@PathVariable Long id){

        Optional<Topic> topic = topicRepository.findById(id);

        return topic.map(value -> ResponseEntity.ok(new TopicsDetailDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    @Transactional
    @CacheEvict(value = "topicsList", allEntries = true)
    public ResponseEntity<TopicDTO> toUpdate(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form){
        Optional<Topic> optional = topicRepository.findById(id);

        if(optional.isPresent()){
            Topic topic = form.toUpdate(id, topicRepository);
            return ResponseEntity.ok(new TopicDTO(topic));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    @CacheEvict(value = "topicsList", allEntries = true)
    public ResponseEntity<?> toRemove(@PathVariable Long id){
        Optional<Topic> topic = topicRepository.findById(id);

        if (topic.isPresent()){
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }
}

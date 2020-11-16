package com.example.mediaPage.topic;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("")
    public List<Topic> getAll(@RequestParam(required = false)
                                      Long articleId) {
        if(articleId == null) {
            return topicService.getAll();
        } else return topicService.getAllByArticleId(articleId);
    }

    @GetMapping("/{id}")
    public Topic getById(@PathVariable Long id) {
        return topicService.getById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public Topic create(@RequestBody Topic topic) {
        return topicService.create(topic);
    }

    @PutMapping("")
    public Topic update(@RequestBody Topic updatedTopic){
        return topicService.update(updatedTopic);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        topicService.delete(id);
    }
}

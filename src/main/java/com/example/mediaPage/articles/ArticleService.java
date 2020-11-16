package com.example.mediaPage.articles;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.mediaPage.topic.Topic;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public List<Article> getAll(String sort) {
        return repository.findAll()
                .stream()
                .sorted(Comparator.comparing(sort.equals("author") ? Article::getAuthorName
                        : Article::getTitle))
                .collect(Collectors.toList());
    }

    public Optional<Article> getById(Long id) {
        return repository.findById(id);
    }

    public Article create(Article newArticle) {
        repository.save(newArticle);
        return newArticle;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Article update(Article updatedArticle) {
        return repository.save(updatedArticle);
    }

    public List<Article> getAll() {
        return repository.findAll();
    }

    public List<Article> getAllByTopicsId(Long topicId) {
        return repository.findAllByTopics_id(topicId);
    }

    public Article addTopic(Long id, Topic topic) {

        return repository.save(repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        ).addTopic(topic));

    }

    public void removeTopic(Long id, Topic topic) {
        Article article;
        article = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        article.deleteTopic(topic);
        repository.save(article);
    }

    public void removeTopic(Topic topic) {
        Long topicId = topic.getId();
        List<Article> articlesContainTopic = repository.findAllByTopics_id(topicId);
        articlesContainTopic.forEach(a -> {
            a.deleteTopic(topic);
        });
    }
}
package com.example.mediaPage.comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public List<Comment> getAll() {
        return repository.findAll().stream().collect(Collectors.toList());
    }

    public Optional<Comment> getById(Long id) {
        return repository.findById(id);
    }

    public Comment create (Comment comment) {
        repository.save(comment);
        return comment;
    }

    //TODO: make the method check if id exists
    public Comment update (Comment updatedComment) {
        return repository.save(updatedComment);
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }

    public List<Comment> getAllById(Long articleId) {
        return repository.findAllByArticleId(articleId);
    }
}
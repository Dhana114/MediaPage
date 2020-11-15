package com.example.mediaPage.comment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("")
    public List<Comment> getComments(@RequestParam(required = false) Long articleId) {
        if(articleId == null) {
            return commentService.getAll();
        } else {
            return commentService.getAllById(articleId);
        }
    }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Long id) {
        return commentService.getById(id)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public Comment create(@RequestBody Comment comment){
        return commentService.create(comment);
    }

    @PutMapping("")
    public Comment update(@RequestBody Comment updatedComment){
        return commentService.update(updatedComment);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id){
        commentService.delete(id);
    }
}

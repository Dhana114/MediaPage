package com.example.mediaPage.topic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.example.mediaPage.articles.Article;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "topic")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "contacts"})
public class Topic {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "topics")
    private List<Article> articles;

    public Topic(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Topic() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addArticle (Article article) {
        articles.add(article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(id, topic.id) &&
                Objects.equals(name, topic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, articles);
    }
}
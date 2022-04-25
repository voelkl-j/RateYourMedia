package com.rateyourmedia.rym_entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Book{
    @Id
    private Long isbn;
    private Date publication_date;
    private String author;
    @OneToMany(mappedBy = "basedOn")
    private Collection<Movie> movieAdaptations;
    @OneToOne(orphanRemoval=true)
    private Media baseInformation;

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Media getBaseInformation() {
        return baseInformation;
    }

    public void setBaseInformation(Media baseInformation) {
        this.baseInformation = baseInformation;
    }

    public Collection<Movie> getMovieAdaptations() {
        return movieAdaptations;
    }

    public void setMovieAdaptations(Collection<Movie> movieAdaptations) {
        this.movieAdaptations = movieAdaptations;
    }
}

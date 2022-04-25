package com.rateyourmedia.rym_entity;

import javax.persistence.*;

@Entity
public class Movie {
    @Id
    private Long movieID;
    private String director;
    private int duration;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Book basedOn;
    @OneToOne(orphanRemoval = true)
    private Media baseInformation;


    public Long getMovieID() {
        return movieID;
    }

    public void setMovieID(Long movieID) {
        this.movieID = movieID;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Book getBasedOn() {
        return basedOn;
    }

    public void setBasedOn(Book basedOn) {
        this.basedOn = basedOn;
    }

    public Media getBaseInformation() {
        return baseInformation;
    }

    public void setBaseInformation(Media baseInformation) {
        this.baseInformation = baseInformation;
    }
}

package com.rateyourmedia.rym_entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue
    private Long reviewID;
    private Date creationDate;
    @Lob
    private String text;
    private int rating;
    @ManyToOne
    private User creator;
    @ManyToOne
    private Media reviewed;

    public Review() {}

    public Review(String text, int rating, User creator, Media reviewed){
        this.creationDate = new Date();
        this.text= text;
        this.rating= rating;
        this.creator= creator;
        this.reviewed= reviewed;
    }

    public void setReviewID(Long reviewID) {
        this.reviewID = reviewID;
    }




    public Long getReviewID() {
        return reviewID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Media getReviewed() {
        return reviewed;
    }

    public void setReviewed(Media reviewed) {
        this.reviewed = reviewed;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creation_date) {
        this.creationDate = creation_date;
    }
}

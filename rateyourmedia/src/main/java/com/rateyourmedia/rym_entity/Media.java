package com.rateyourmedia.rym_entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
public class Media {
    @Id
    @GeneratedValue
    private Long mediaID;
    @Enumerated(EnumType.ORDINAL)
    private RYMediaType RYMediaType;
    private String title;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    @OneToMany(mappedBy = "reviewed", orphanRemoval = true)
    private Collection<Review> reviewsM;

    public Media() {
    }

    public void setMediaID(Long mediaID) {
        this.mediaID = mediaID;
    }
    public Long getMediaID() {
        return mediaID;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getDescription(){
        return description;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }

    public void setMediaType(RYMediaType RYMediaType) {
        this.RYMediaType = RYMediaType;
    }
    public RYMediaType getMediaType(){
        return RYMediaType;
    }
}

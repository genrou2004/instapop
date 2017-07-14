package com.instapop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by raya on 7/13/17.
 */
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String username;
    private String imgSrc;
    private String caption;
    private String imgName;
    private int numberOfLikes;
    private int filterIndex;
    private String firstFilterLink;
    private String secondFilterLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public int getFilterIndex() {
        return filterIndex;
    }

    public void setFilterIndex(int filterIndex) {
        this.filterIndex = filterIndex;
    }

    public String getFirstFilterLink() {
        return firstFilterLink;
    }

    public void setFirstFilterLink(String firstFilterLink) {
        this.firstFilterLink = firstFilterLink;
    }

    public String getSecondFilterLink() {
        return secondFilterLink;
    }

    public void setSecondFilterLink(String secondFilterLink) {
        this.secondFilterLink = secondFilterLink;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

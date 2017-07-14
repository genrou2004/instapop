package com.instapop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by raya on 7/13/17.
 */
@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int imageId;
    private String username;
    private  int number_likes;

    public Likes(int imageId, String username, int number_likes) {
        this.imageId = imageId;
        this.username = username;
        this.number_likes = number_likes;
    }

    public Likes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumber_likes() {
        return number_likes;
    }

    public void setNumber_likes(int number_likes) {
        this.number_likes = number_likes;
    }
}

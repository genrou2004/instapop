package com.instapop.repository;

import com.instapop.model.Likes;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by raya on 7/13/17.
 */
public interface LikesRepository extends CrudRepository<Likes,Integer>  {
    ArrayList<Likes> findAllByUsername(String username);
    ArrayList<Likes> findAllByImageId(int id);
}

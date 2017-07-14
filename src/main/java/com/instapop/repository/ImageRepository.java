package com.instapop.repository;

import com.instapop.model.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by raya on 7/13/17.
 */
public interface ImageRepository extends CrudRepository<Image, Integer>{
    public List<Image> findAllById(int id);


    public List<Image> findAll();
}

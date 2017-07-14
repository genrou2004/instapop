package com.instapop.repository;

import com.instapop.model.Comment;
import com.instapop.model.Follow;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by raya on 7/13/17.
 */
public interface CommentRepository extends CrudRepository<Comment,Integer>  {
    public List<Comment> findAllByImageId(long imageId);
}

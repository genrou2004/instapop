package com.instapop.repository;

import com.instapop.model.Follow;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by raya on 7/13/17.
 */
public interface FollowRepository extends CrudRepository<Follow,Integer>  {

    Follow findByFollowerAndFollowed(String follower, String follwed);

    List<Follow> findByFollower(String follower);

    List<Follow> findByFollowed(String follwed);
}

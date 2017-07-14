package com.instapop.repository;

import com.instapop.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by raya on 7/13/17.
 */
public interface UserRepository extends CrudRepository<User,Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);
}

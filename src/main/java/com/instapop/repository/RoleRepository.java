package com.instapop.repository;

import com.instapop.model.Role;
import com.instapop.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by raya on 7/13/17.
 */
public interface RoleRepository extends CrudRepository<Role, Integer>{
    public Role findByRole(String role);
}

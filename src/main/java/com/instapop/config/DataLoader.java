package com.instapop.config;

import com.instapop.model.Role;
import com.instapop.repository.RoleRepository;
import com.instapop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by raya on 7/13/17.
 */
@Component
public class DataLoader implements CommandLineRunner {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        if (roleRepository.findByRole("USER")==null) {
            System.out.println("Loading data . . .");

            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));

            Role adminRole = roleRepository.findByRole("ADMIN");
            Role userRole = roleRepository.findByRole("USER");


        }
    }
}

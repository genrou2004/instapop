package com.instapop.config;

import com.instapop.model.User;
import com.instapop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by raya on 7/13/17.
 */
@Component
public class UserValidator implements Validator {
    @Autowired
    UserRepository userRepository;
    public boolean supports(Class<?> clazz){
        return User.class.isAssignableFrom(clazz);
    }
    public void validate(Object target, Errors errors){
        User user = (User) target;
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "user.username.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty");
        if(username.length() < 5){
            errors.rejectValue("username","user.username.tooShort");
        }
        if(password.length() < 5){
            errors.rejectValue("password","user.password.tooShort");
        }
        if(userRepository.countByEmail(email)>0){
            errors.rejectValue("email", "user.email.duplicate");
        }
        if(userRepository.countByUsername(username)>0){
            errors.rejectValue("username", "user.username.duplicate");
        }
    }
    /*public void validateCaptions(Object target, Errors errors)
    {
        Meme meme= (Meme) target;
        String top=meme.getTop();
        String bottom=meme.getBottom();
        if(top.length() > 50){
            errors.rejectValue("top","meme.top.tooLong");
        }
        if(bottom.length() > 50){
            errors.rejectValue("bottom","meme.bottom.tooLong");
        }

    }*/

}

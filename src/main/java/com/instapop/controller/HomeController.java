
package com.instapop.controller;


import com.cloudinary.utils.ObjectUtils;
import com.instapop.config.CloudinaryConfig;
import com.instapop.config.UserValidator;
import com.instapop.model.*;
import com.instapop.repository.*;
import com.instapop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

/**
 * Created by raya on 7/13/17.
 */

@Controller
public class HomeController {

    @Autowired
    UserValidator userValidator;
    @Autowired
    UserService userService;
    @Autowired
    CloudinaryConfig cloudc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    LikesRepository likesRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    FollowRepository followRepository;

    @RequestMapping("/")
    public String home(Model model) {

        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";

    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        model.addAttribute("user", user);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "registration";
        }
        userService.saveUser(user);
        model.addAttribute("message", "User Account Successfully Created");

        return "home";
    }

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        model.addAttribute("image", new Image());
        return "upload";
    }

    @PostMapping("/upload")
    public String singleImageUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, @ModelAttribute Image image, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "upload";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            model.addAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            model.addAttribute("imageurl", uploadResult.get("url"));
            String filename = uploadResult.get("public_id").toString() + "." + uploadResult.get("format").toString();
            //model.addAttribute("sizedimageurl", cloudc.createUrl(filename,300,400, "scale"));
            image.setImgName(filename);
            image.setImgSrc(uploadResult.get("url").toString());
            image.setFilterIndex(0);
            image = filterColor(image);
            //image.setImgsrc((String)  cloudc.createUrl(filename,300,400, "scale"));
            // imageRepository.save(image);
            //  model.addAttribute("imageList", imageRepository.findAll());
            model.addAttribute("image", image);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Sorry I can't upload that!");
        }
        return "preview";
    }

    @RequestMapping("preview")
    public String previewImage(@ModelAttribute Image image, Model model) {
        image = filterColor(image);
        model.addAttribute("image", image);


        return "preview";
    }


    @RequestMapping("/save")
    public String processSave(Image image, Authentication authentication) {
        User user = getUser(authentication);
        image.setUsername(user.getUsername());
        imageRepository.save(image);
        return "home";
    }


    private Image filterColor(Image image) {

        if (image.getFilterIndex() == 1) {

                image.setSecondFilterLink((String) cloudc.createColorImageSize(image.getImgName(), "blue", 300, 400, "scale"));
                image.setFirstFilterLink((String) cloudc.createColorImage(image.getImgName(), "blue", 500, 500, "scale"));
                return image;}
            else if (image.getFilterIndex() == 2) {
                image.setSecondFilterLink((String) cloudc.createColorImageSize(image.getImgName(), "yellow", 300, 400, "scale"));
                image.setFirstFilterLink((String) cloudc.createColorImage(image.getImgName(), "yellow", 500, 500, "scale"));
                return image;
            }
        else if (image.getFilterIndex() == 3) {
            image.setSecondFilterLink((String) cloudc.createColorImageSize(image.getImgName(), "green", 300, 400, "scale"));
            image.setFirstFilterLink((String) cloudc.createColorImage(image.getImgName(), "green", 500, 500, "scale"));
            return image;
        }
        else if (image.getFilterIndex() == 4) {
            image.setSecondFilterLink((String) cloudc.createColorImageSize(image.getImgName(), "red", 300, 400, "scale"));
            image.setFirstFilterLink((String) cloudc.createColorImage(image.getImgName(), "red", 500, 500, "scale"));
            return image;
        }
            else{
                image.setSecondFilterLink((String) cloudc.createUrl(image.getImgName(), 300, 400, "scale"));
                image.setFirstFilterLink(image.getImgSrc());
                return image;
        }
    }

    private User getUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername());
    }


}
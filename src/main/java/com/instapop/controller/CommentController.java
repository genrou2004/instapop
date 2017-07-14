package com.instapop.controller;
import com.instapop.model.Comment;
import com.instapop.model.Image;
import com.instapop.model.Likes;
import com.instapop.model.User;
import com.instapop.repository.CommentRepository;
import com.instapop.repository.ImageRepository;
import com.instapop.repository.LikesRepository;
import com.instapop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by raya on 7/14/17.
 */
@Controller
public class CommentController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    LikesRepository likesRepository;

    @PostMapping("/comment")
    public String postComment(@ModelAttribute Comment comment, Principal principal)
    {
        User user=userRepository.findByUsername(principal.getName());
        comment.setId(user.getId());
        comment.setDate(new Date());
        comment.setUsername(user.getUsername());
        commentRepository.save(comment);

        return "redirect:/photo/"+comment.getImageId();
    }

    @RequestMapping("/photo/{id}")
    public String viewPhoto(@PathVariable("id") int id, Model model)
    {
        Image image=imageRepository.findOne(id);
        model.addAttribute("image",image);
        User user=userRepository.findOne(image.getId());
        model.addAttribute("user",user);
        model.addAttribute("commentList",commentRepository.findAllByImageId(image.getId()));
        Comment comment=new Comment();
        comment.setImageId(image.getId());
        model.addAttribute("comment",comment);


        return "photo";
    }

    @RequestMapping("/profile")
    public String viewMyProfile(@PathVariable("id") long id, Model model, Principal principal)
    {
        User user=userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("imageList",imageRepository.findAllById(user.getId()));

        return "profile";
    }
    @RequestMapping("/profile/{id}")
    public String viewProfile(@PathVariable("id") int id, Model model, Principal principal)
    {
        User user=userRepository.findOne(id);
        model.addAttribute("imageList",imageRepository.findOne(user.getId()));
        model.addAttribute("user", user);

        return "profile";
    }


    @RequestMapping(value = "/showcomment", method = RequestMethod.GET)
    public String getComment(Model model){
        model.addAttribute("comobj",new Comment());
        model.addAttribute(new Image());
        return "showcomment";
    }
    @RequestMapping(value = "/showcomment", method = RequestMethod.POST)
    public String processComment(@ModelAttribute Comment comment, Model model, Image image){
        model.addAttribute("comment",new Comment());
        model.addAttribute(new Image());
        //model.addAttribute("findComment",commentRepository.findAllByImageId())
        Iterable<Comment> commentList = commentRepository.findAllByImageId(image.getId());
        model.addAttribute("commentList",commentList);
        return "base";
    }
    @RequestMapping(value = "/likes", method = RequestMethod.GET)
    public String getLikes(Model model){
        model.addAttribute("likes",new Likes());
        return "likes";
    }
    @RequestMapping(value = "/likes", method = RequestMethod.POST)
    public String processLikes(@ModelAttribute Likes likes, Model model, Principal principal, Image image){
        model.addAttribute("photo",new Image());
        // System.out.print("NUMBER IS " + likes.getNumber_likes());
        likes.setNumber_likes(likes.getNumber_likes() + 1);
        likes.setUsername(principal.getName());
        likes.setImageId(image.getId());
        likesRepository.save(likes);
        return "redirect:/photoGalary";
    }
    }


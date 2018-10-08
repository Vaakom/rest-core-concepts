package com.core.rest.controllers;

import com.core.rest.domain.Post;
import com.core.rest.domain.User;
import com.core.rest.services.UserService;
import com.core.rest.utils.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private UserService service;

    @GetMapping(path = "/users/{id}/posts")
    public List<Post> getAllPosts(@PathVariable Long id) {
        User user = service.findOne(id);
        if(user == null)
            throw new UserNotFoundException("User id = " + id);

        return user.getPostList();
    }


}

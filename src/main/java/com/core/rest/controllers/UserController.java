package com.core.rest.controllers;

import com.core.rest.domain.User;
import com.core.rest.services.UserService;
import com.core.rest.utils.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return service.findAll();
    }

// HATEOAS example
    @GetMapping(path = "/users/{id}")
    public Resource<User> getUser(@PathVariable Long id) {
        User user = service.findOne(id);

        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User createdUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(createdUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUsers(@PathVariable Long id) {
        boolean success = service.delete(id);

        if(!success)
            throw new UserNotFoundException("User id = " + id);

    }

}

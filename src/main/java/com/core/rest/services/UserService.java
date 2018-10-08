package com.core.rest.services;

import com.core.rest.domain.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findOne(Long id);

    public User findOneNullCheck(Long id);

    public User save(User user);

    public boolean delete(Long id);

}

package com.core.rest.services;

import com.core.rest.domain.User;
import com.core.rest.repository.UserRepository;
import com.core.rest.utils.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceJPAImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        Sort sort = new Sort(Sort.Direction.ASC, "name");
        return repository.findAll(sort);
    }

    @Override
    public User findOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User findOneNullCheck(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("User id = " + id));
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}

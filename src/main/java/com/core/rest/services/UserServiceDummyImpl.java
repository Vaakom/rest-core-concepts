package com.core.rest.services;

import com.core.rest.domain.User;
import com.core.rest.utils.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class UserServiceDummyImpl implements UserService{

    private static final List<User> list = new CopyOnWriteArrayList<>();

    private static int counter = 0;

    static {
        addToList(new User(1L, "Adam", new Date()));
        addToList(new User(2L, "Eva", new Date()));
        addToList(new User(3L, "Lilith", new Date()));
    }

    public List<User> findAll(){
        return list;
    }

    public User findOne(Long id){
        return list.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public User findOneNullCheck(Long id) {
        return list.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(() -> new UserNotFoundException("User id = " + id));
    }

    public User save(User user){
        return addToList(user);
    }

    public boolean delete(Long id){
        User user = findOne(id);
        if(user == null)
            return false;


        return list.remove(user);
    }

    private static User addToList(User user){
        user.setId(new Long(++counter));
        list.add(user);
        return user;
    }

}

package com.core.rest.services;

import com.core.rest.domain.User;
import com.core.rest.utils.exceptions.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void findAll() {
        assertTrue(service.findAll().size() == 3);
    }

    @Test
    public void findOne() {
        assertEquals("Lilith", service.findOne(1003L).getName());
    }

    @Test
    public void findOneNull() {
        assertNull(service.findOne(12345L));
    }

    @Test
    public void findOneNullCheck() {
        assertEquals("Lilith", service.findOneNullCheck(1003L).getName());
    }

    @Test(expected = UserNotFoundException.class)
    public void findOneNullCheckNull() {
        service.findOneNullCheck(12345L);
    }

    @Test
    @DirtiesContext
    public void save() {
        User user = new User(null, "Luna", new Date());
        user = service.save(user);

        assertNotNull(user.getId());
    }

    @Test
    @DirtiesContext
    public void delete() {
        service.delete(1003L);
        assertNull(service.findOne(1003L));
    }
}
package com.oshcherbak.repository;

import com.oshcherbak.model.User;
import com.oshcherbak.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Before
//    @Rollback(false)
    public void setUp() {
        List<User> users = new ArrayList<>();
        users.add(new User("test name 1", "test surname 1"));
        users.add(new User("test name 2", "test surname 2"));
        users.add(new User("test name 3", "test surname 3"));
        users.add(new User("test name 4", "test surname 4"));

        userRepository.save(users);
    }

    @Test
    public void findAllTest(){
        Iterable<User> users = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);

        assertEquals(4, userList.size());
    }

    @Test
    public void existsTrueTest(){
        boolean actual = userRepository.exists(2l);
        assertTrue(actual);
    }

    @Test
    public void existsFalseTest(){
        boolean actual = userRepository.exists(8l);
        assertFalse(actual);
    }

    @Test
    public void existsReSaveTest(){
        User user = userRepository.findOne(1l);
        user.setFirstName("change name");
        userRepository.save(user);

        User user1 = userRepository.findOne(1l);

        assertEquals(user, user1);
    }
}
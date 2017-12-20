package com.oshcherbak.service.impl;
import com.oshcherbak.AppConfig;
import com.oshcherbak.model.User;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(AppConfig.class)
@DataJpaTest
@Transactional
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Before
//    @Rollback(false)
    public void setUp() {
//        try {
//            dataSource.getConnection()
//                    .createStatement()
//                    .execute("");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            Assert.fail();
//        }


        List<User> users = new ArrayList<>();
        users.add(new User("test name 1", "test surname 1"));
        users.add(new User("test name 2", "test surname 2"));
        users.add(new User("test name 3", "test surname 3"));
        users.add(new User("test name 4", "test surname 4"));

        userService.save(users.get(0));

//        userService.save(users);
    }

    @Test
    void findOne() {
    }

    @Test
    void findAll() {
        Iterable<User> users = userService.findAll();
        List<User> userList = new ArrayList<>();
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}
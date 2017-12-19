package com.oshcherbak.service.impl;

import com.oshcherbak.model.User;
import com.oshcherbak.repository.UserRepository;
import com.oshcherbak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public User findOne(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    @Override
    public void save(User ob) {
        userRepository.save(ob);
    }

    @Override
    public void delete(User ob) {
        userRepository.delete(ob);
    }

    @Override
    public void update(User ob) {
        userRepository.save(ob);
    }
}

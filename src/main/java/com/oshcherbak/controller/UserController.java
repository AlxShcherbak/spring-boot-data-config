package com.oshcherbak.controller;

import com.oshcherbak.model.User;
import com.oshcherbak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserController() {
    }

    @RequestMapping("api/users")
    @ResponseBody
    public List<User> getUsers() {
        List<User> users = userService.findAll();
        return users;
    }
}

package com.oshcherbak.controller;

import com.oshcherbak.model.User;
import com.oshcherbak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserController() {
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getUser(@PathVariable("id") int id) {
        User user = userService.findOne(id);
        return user != null
                ? new ResponseEntity<User>(user, HttpStatus.OK)
                : new ResponseEntity<String>("user not found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "user/create", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        userService.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "user/update/{id}", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {

        User currentUser = userService.findOne(id);
        if (currentUser == null){
            return new ResponseEntity<String>("Unable to update. User with id " + id + " not found.", HttpStatus.NOT_FOUND);
        }

        userService.update(user);

        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "user/update", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> updateUser(@RequestBody User user) {

        User currentUser = userService.findOne(user.getId());
        if (currentUser == null){
            return new ResponseEntity<String>("Unable to update. User with id " + user.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
        userService.update(user);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
}

package com.oshcherbak.controller;

import com.oshcherbak.model.User;
import com.oshcherbak.service.UserService;
import com.oshcherbak.utils.RestWrapper;
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
        List<User> users = null;
        try {
            users = userService.findAll();
        } catch (Exception e){
            return new ResponseEntity<>(
                    new RestWrapper<>(users, "unable to find users, something went wrong", e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }
        if (users == null || users.isEmpty()){
            return new ResponseEntity<>(
                    new RestWrapper<>(users, "users not found", null),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                new RestWrapper<>(users, HttpStatus.OK.toString(), null),
                HttpStatus.OK);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getUser(@PathVariable("id") int id) {

        User user = null;
        try {
            user = userService.findOne(id);
        } catch (Exception e){
            return new ResponseEntity<>(
                    new RestWrapper<>(user, "unable to find user, something went wrong", e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }
        if (user == null){
            return new ResponseEntity<>(
                    new RestWrapper<>(user, "user not found", null),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                new RestWrapper<>(user, HttpStatus.OK.toString(), null),
                HttpStatus.OK);
    }

    @RequestMapping(value = "user/create", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {

        try {
            userService.save(user);
        } catch (Exception e){
            return new ResponseEntity<>(
                    new RestWrapper<>(user, "unable to create user, something went wrong", e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());

        return new ResponseEntity<>(
                new RestWrapper<>(headers, HttpStatus.CREATED.toString(), null),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "user/update/{id}", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User currentUser = null;
        try {
            currentUser = userService.findOne(id);
        } catch (Exception e){
            return new ResponseEntity<>(
                    new RestWrapper<>(currentUser, "unable to update user, something went wrong, user not found", e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }
        if (currentUser == null){
            return new ResponseEntity<>(
                    new RestWrapper<>(currentUser, "unable to update. User with id " + id + " not found.", null),
                    HttpStatus.NOT_FOUND);
        }

        try {
            userService.update(user);
        } catch (Exception e){
            return new ResponseEntity<>(
                    new RestWrapper<>(currentUser, "unable to update user, something went wrong", e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                new RestWrapper<>(currentUser, HttpStatus.OK.toString(), null),
                HttpStatus.OK);
    }

    @RequestMapping(value = "user/update", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> updateUser(@RequestBody User user) {

        User currentUser = null;
        try {
            currentUser = userService.findOne(user.getId());
        } catch (Exception e){
            return new ResponseEntity<>(
                    new RestWrapper<>(currentUser, "unable to update user, something went wrong, user not found", e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }
        if (currentUser == null){
            return new ResponseEntity<>(
                    new RestWrapper<>(currentUser, "unable to update. User with id " + user.getId() + " not found.", null),
                    HttpStatus.NOT_FOUND);
        }

        try {
            userService.update(user);
        } catch (Exception e){
            return new ResponseEntity<>(
                    new RestWrapper<>(currentUser, "unable to update user, something went wrong", e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                new RestWrapper<>(currentUser, HttpStatus.OK.toString(), null),
                HttpStatus.OK);
    }
}

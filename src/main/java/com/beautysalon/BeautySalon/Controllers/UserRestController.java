package com.beautysalon.BeautySalon.Controllers;

import com.beautysalon.BeautySalon.Entities.User;
import com.beautysalon.BeautySalon.Repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("ROLE_ADMIN")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> users (){
        return  (List<User>)userRepository.findAll();
    }

    @PreAuthorize("ROLE_ADMIN")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> userById(@PathVariable long id){
        User user = userRepository.findOne(id);
        if(user == null){
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }
    }

    @PreAuthorize("ROLE_ADMIN")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable long id){
        User user = userRepository.findOne(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedEmail = authentication.getName();
        if(user == null){
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        } else if(user.getEmail().equalsIgnoreCase(loggedEmail)){
            throw new RuntimeException("You can not delete your acount");
        } else {
            userRepository.delete(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
    }

    @PreAuthorize("ROLE_Admin")
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user){
        if(userRepository.findByEmail(user.getEmail()) == null && userRepository.findByEmail(user.getEmail()).getId() != user.getId()){
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }



}

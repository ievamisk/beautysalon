package com.beautysalon.BeautySalon.Controllers;

import com.beautysalon.BeautySalon.Entities.User;
import com.beautysalon.BeautySalon.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/user/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    User getUser(@PathVariable(value = "id") long id) {
        return userRepository.findOne(id);
    }
}

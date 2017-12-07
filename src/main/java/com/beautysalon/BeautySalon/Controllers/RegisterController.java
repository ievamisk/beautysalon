package com.beautysalon.BeautySalon.Controllers;
import com.beautysalon.BeautySalon.Entities.Role;
import com.beautysalon.BeautySalon.Entities.User;
import com.beautysalon.BeautySalon.Repositories.RoleRepository;
import com.beautysalon.BeautySalon.Repositories.UserRepository;
import com.beautysalon.BeautySalon.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;


    public @ResponseBody
    @RequestMapping(value="/", method = RequestMethod.POST, produces = "application/json")
    User newUser(
            @RequestParam(name = "first_name") String firstName,
            @RequestParam(name = "last_name") String lastName,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "phone_number") int phoneNumber,
            @RequestParam(name = "role_id") long role_id
    ){
        Role role = roleRepository.findOne(role_id);
        User user = new User(firstName, lastName, email, password, phoneNumber);
        user.setRole(role);
        userService.saveUser(user);

//        try{
//            User userExists = userService.findByEmail(user.getEmail());
////        System.out.println(userExists);
//
//            if (userExists != null) {
//                System.out.println("User with this email already exists");
//
//            }
//            else {
//                userRepository.save(user);
//                System.out.println("User successfully added");
//
//            }
//        }
//        catch (Exception ex) {
//            System.out.println(ex);
//        }
        return user;
    }
}

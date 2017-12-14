package com.beautysalon.BeautySalon.Controllers;

import com.beautysalon.BeautySalon.Entities.User;
import com.beautysalon.BeautySalon.Repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user")
    public User user(Principal principal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserEmail = authentication.getName();
        return userRepository.findByEmail(loggedUserEmail);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/authenticate")
    public ResponseEntity<Map<String, Object>> login (@RequestParam String email, @RequestParam String password, HttpServletResponse response)
    {
        String token= null;
        User user = userRepository.findByEmail(email);
        Map<String,Object> tokenMap = new HashMap<>();
        if(user != null && user.getPassword().equals(password)){
            token = Jwts.builder().setSubject(email).claim("roles", user.getRole()).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretKey").compact();
            tokenMap.put("token", token);
            tokenMap.put("user", user);
            return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);
        } else {
            tokenMap.put("token", null);
            return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.UNAUTHORIZED);
        }
    }


}

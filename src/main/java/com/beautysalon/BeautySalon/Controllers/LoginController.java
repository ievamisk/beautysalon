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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/user")
    public User user(Principal principal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserEmail = authentication.getName();
        return userRepository.findByEmail(loggedUserEmail);
    }


    @RequestMapping(value = "/",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Map<String, Object>> login (@RequestParam(name = "email") String email, @RequestParam (name = "password") String password, HttpServletResponse response)
    {
        String token= null;
        User user = userRepository.findByEmail(email);
        Map<String,Object> tokenMap = new HashMap<>();
        if(user != null && bCryptPasswordEncoder.matches(password,user.getPassword())){
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

package com.example.Sample.Project.Controllers;

import com.example.Sample.Project.utils.JWTUtil;

import com.example.Sample.Project.DataAccessObject.UserDao;
import com.example.Sample.Project.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDao userdao;

    @Autowired
    private JWTUtil jwtutil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String registerController(@RequestBody User newUser) {

        User newUserInfo = userdao.getUserByCredentials(newUser);
        if(newUserInfo != null){

            String tokenJWT = jwtutil.create(String.valueOf(newUserInfo.getId()),newUserInfo.getEmail());
            return tokenJWT;
        } else {
            return "FAIL";
        }
    }
}

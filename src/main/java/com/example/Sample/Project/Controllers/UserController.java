package com.example.Sample.Project.Controllers;

import com.example.Sample.Project.DataAccessObject.UserDao;
import com.example.Sample.Project.Model.User;
import com.example.Sample.Project.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//Used to control our URL's
@RestController
public class UserController {

    @Autowired
    private UserDao userdao;

    @Autowired
    private JWTUtil jwtutil;

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        User user = new User();
        user.setName("Oscar");
        user.setId(id);
        user.setEmail("omarques@gmail.com");
        user.setPhone("7857383u");
        user.setPassword("oscarito99");

        return user;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token){

        if (!validateToken(token)){
            return null;
        }
        return userdao.getUser();
    }

    private boolean validateToken(String token){
        String userID = jwtutil.getKey(token);
        return userID != null;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerController(@RequestBody User newUser){

        //-------------------//
        //     IMPORTANT     //
        //-------------------//
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashPassword = argon2.hash(1, 1024, 1,newUser.getPassword());

        //Save new hash password in the database
        newUser.setPassword(hashPassword);


         userdao.register(newUser);
    }

    public User modifyUser(){
        User user = new User();
        user.setName("Oscar");
        user.setEmail("omarques@gmail.com");
        user.setPhone("7857383u");
        user.setPassword("oscarito99");

        return user;
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String token,
                           @PathVariable Long id){
        if (!validateToken(token)){
            return;
        }
        userdao.delete(id);
    }
}

package com.example.Sample.Project.DataAccessObject;

import com.example.Sample.Project.Model.User;

import java.util.List;

public interface UserDao {

    List<User> getUser();

    void delete(Long id);

    void register(User newUser);

    User getUserByCredentials(User newUser);
}

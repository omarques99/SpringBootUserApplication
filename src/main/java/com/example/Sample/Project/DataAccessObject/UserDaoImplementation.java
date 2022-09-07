package com.example.Sample.Project.DataAccessObject;

import com.example.Sample.Project.Model.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImplementation implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUser() {

        String query = "FROM User";
        List<User> output = entityManager.createQuery(query).getResultList();

        return output;
    }

    @Override
    public void delete(Long id) {
        User delUser = entityManager.find(User.class, id);
        entityManager.remove(delUser);
    }

    @Override
    public void register(User newUser) {
        entityManager.merge(newUser);
    }


    @Override
    public User getUserByCredentials(User newUser) {
        String query = "FROM User WHERE email = :email";
        List<User> list = entityManager.createQuery(query)
                .setParameter("email", newUser.getEmail()).getResultList();

        if (list.isEmpty()) {
            return null;
        }
        String passwordHashed = list.get(0).getPassword();

        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        if (argon.verify(passwordHashed, newUser.getPassword())) {
            return list.get(0);
        }
        return null;
    }
}

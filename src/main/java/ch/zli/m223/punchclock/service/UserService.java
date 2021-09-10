package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserService {
    @Inject
    EntityManager entityManager;

    public UserService() {
    }

    @Transactional
    public User createUser(User user) {
        entityManager.merge(user);
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<User> findSpecialUser() {
        var query = entityManager.createQuery("FROM User HAVING LENGTH(email) > 10");
        return query.getResultList();
    }

    @Transactional
    public void deleteUser(Long id){
        User removeUser = getUserById(id);
        entityManager.remove(removeUser);
    }

    @Transactional
    public User getUserById(Long id){
        return entityManager.find(User.class, id);
    };

    @Transactional
    public void update(User user){
        entityManager.merge(user);
    }

    @Transactional
    public User getLoginUser(String email, String password) {
        var query = entityManager.createQuery("FROM User WHERE email = :email and" +
                " password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        return (User) query.getSingleResult();
    }

}

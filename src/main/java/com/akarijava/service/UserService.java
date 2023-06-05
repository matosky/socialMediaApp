package com.akarijava.service;

import com.akarijava.model.User;
import com.akarijava.persistence.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class UserService {
    public void registerUser(User user) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        entityManager.close();
    }

    public User getUserById(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        User user = entityManager.find(User.class, id);
        entityManager.close();
        return user;
    }

    public List<User> getAllUsers() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Query query = entityManager.createQuery("SELECT u FROM User u");
        List<User> users = query.getResultList();
        entityManager.close();
        return users;
    }

    public User signIn(String username, String password) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        User user = (User) query.getSingleResult();
        entityManager.close();
        return user;
    }
}

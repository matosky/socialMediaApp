package com.akarijava.service;

import com.akarijava.model.Post;
import com.akarijava.model.User;
import com.akarijava.persistence.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class PostService {
    public void createPost(Long userId, String title, String content) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        User user = entityManager.find(User.class, userId);
        Post post = new Post(title, content, user);
        entityManager.persist(post);

        transaction.commit();
        entityManager.close();
    }

    public Post getPostById(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Post post = entityManager.find(Post.class, id);
        entityManager.close();
        return post;
    }

    public List<Post> getAllPosts() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Query query = entityManager.createQuery("SELECT p FROM Post p");
        List<Post> posts = query.getResultList();
        entityManager.close();
        return posts;
    }
}

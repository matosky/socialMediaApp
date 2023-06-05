package com.akarijava.service;

import com.akarijava.model.Comment;
import com.akarijava.model.Post;
import com.akarijava.model.User;
import com.akarijava.persistence.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class CommentService {
    public void createComment(Long userId, Long postId, String content) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        User user = entityManager.find(User.class, userId);
        Post post = entityManager.find(Post.class, postId);
        Comment comment = new Comment(content, user, post);
        entityManager.persist(comment);

        transaction.commit();
        entityManager.close();
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM Comment c WHERE c.post.id = :postId");
        query.setParameter("postId", postId);
        List<Comment> comments = query.getResultList();
        entityManager.close();
        return comments;
    }
}

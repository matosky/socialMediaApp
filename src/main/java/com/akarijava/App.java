package com.akarijava;

import com.akarijava.model.Comment;
import com.akarijava.model.Post;
import com.akarijava.model.User;
import com.akarijava.service.CommentService;
import com.akarijava.service.PostService;
import com.akarijava.service.UserService;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Create instances of service classes
        UserService userService = new UserService();
        PostService postService = new PostService();
        CommentService commentService = new CommentService();

        // Register a user
        User user = new User("john_doe",  "John Doe");
        userService.registerUser(user);
        Long userId = user.getId();
        System.out.println("User registered successfully with ID: " + userId);

        // Create a post
        postService.createPost(userId, "My First Post", "Hello, world!");
        System.out.println("Post created successfully");

        // Get all posts
        List<Post> posts = postService.getAllPosts();
        System.out.println("All Posts:");
        for (Post post : posts) {
            System.out.println("ID: " + post.getId() + ", Title: " + post.getTitle() + ", Content: " + post.getContent());
        }

        // Create a comment on a post
        Long postId = posts.get(0).getId();
        commentService.createComment(userId, postId, "Great post!");
        System.out.println("Comment created successfully");

        // Get all comments for a post
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        System.out.println("Comments for Post ID " + postId + ":");
        for (Comment comment : comments) {
            System.out.println("ID: " + comment.getId() + ", Content: " + comment.getContent());
        }
    }
}

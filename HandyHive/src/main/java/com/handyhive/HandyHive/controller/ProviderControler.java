package com.handyhive.HandyHive.controller;

import com.handyhive.HandyHive.model.Post;
import com.handyhive.HandyHive.model.Comment;
import com.handyhive.HandyHive.repository.PostRepository;
import com.handyhive.HandyHive.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/provider")
public class ProviderControler {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    // Get all posts for a provider (providerId passed as query parameter)
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getProviderPosts(@RequestParam Long providerId) {
        List<Post> allPosts = postRepository.findAll();
        List<Post> providerPosts = allPosts.stream()
            .filter(post -> post.getProvider().getProviderId().equals(providerId))
            .toList();
        return ResponseEntity.ok(providerPosts);
    }

    // Get a single post by its ID (used in the edit page)
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        return optionalPost.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new service listing (post)
    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post savedPost = postRepository.save(post);
        return ResponseEntity.ok(savedPost);
    }

    // Update an existing post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post updatedPost) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if(optionalPost.isPresent()){
            Post existingPost = optionalPost.get();
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setDescription(updatedPost.getDescription());
            existingPost.setImageUrl(updatedPost.getImageUrl());
            postRepository.save(existingPost);
            return ResponseEntity.ok(existingPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postRepository.deleteById(postId);
        return ResponseEntity.ok("Post deleted successfully.");
    }

    // Provider replies to a comment (customer review)
    @PostMapping("/comments/{commentId}/reply")
    public ResponseEntity<Comment> replyToComment(@PathVariable Long commentId, @RequestBody String providerReply) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isPresent()){
            Comment comment = optionalComment.get();
            comment.setProviderReply(providerReply);
            commentRepository.save(comment);
            return ResponseEntity.ok(comment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Dummy endpoint to return provider statistics
    @GetMapping("/statistics")
    public ResponseEntity<ProviderStatistics> getProviderStatistics(@RequestParam Long providerId) {
        ProviderStatistics stats = new ProviderStatistics();
        stats.setTotalPosts(10);
        stats.setTotalComments(25);
        stats.setAverageRating(4.5);
        return ResponseEntity.ok(stats);
    }
}

// DTO for provider statistics
class ProviderStatistics {
    private int totalPosts;
    private int totalComments;
    private double averageRating;

    public int getTotalPosts() {
        return totalPosts;
    }
    public void setTotalPosts(int totalPosts) {
        this.totalPosts = totalPosts;
    }
    public int getTotalComments() {
        return totalComments;
    }
    public void setTotalComments(int totalComments) {
        this.totalComments = totalComments;
    }
    public double getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}

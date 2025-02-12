package com.handyhive.HandyHive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handyhive.HandyHive.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost_PostId(Long postId);
}
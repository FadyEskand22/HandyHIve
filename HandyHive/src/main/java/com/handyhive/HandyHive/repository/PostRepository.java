package com.handyhive.HandyHive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handyhive.HandyHive.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
}
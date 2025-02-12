package com.handyhive.HandyHive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handyhive.HandyHive.model.Rating;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByPost_PostId(Long postId);
}
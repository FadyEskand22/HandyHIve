package com.handyhive.HandyHive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handyhive.HandyHive.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
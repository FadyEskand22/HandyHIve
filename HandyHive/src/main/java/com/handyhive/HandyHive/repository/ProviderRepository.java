package com.handyhive.HandyHive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.handyhive.HandyHive.model.Provider;
import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findByEmail(String email);
}

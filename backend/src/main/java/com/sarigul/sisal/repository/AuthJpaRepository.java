package com.sarigul.sisal.repository;

import com.sarigul.sisal.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthJpaRepository extends JpaRepository<Auth,Integer> {
    Optional<Auth> findByEmail(String email);
}

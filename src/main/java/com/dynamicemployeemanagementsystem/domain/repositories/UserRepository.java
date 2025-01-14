package com.dynamicemployeemanagementsystem.domain.repositories;

import com.dynamicemployeemanagementsystem.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE (:query IS NULL OR u.firstName LIKE %:query% OR u.lastName like %:query%) AND u.deleted = false")
    Page<User> search(@Param("query") String query, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.id = :id AND u.deleted = false")
    Optional<User> find(@Param("id") Long id);

    Optional<User> findByUsername(String username);
}

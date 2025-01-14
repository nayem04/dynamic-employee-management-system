package com.dynamicemployeemanagementsystem.domain.repositories;

import com.dynamicemployeemanagementsystem.domain.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE (:query IS NULL OR r.name LIKE %:query%) AND r.deleted = false")
    Page<Role> search(@Param("query") String query, Pageable pageable);

    @Query("SELECT r FROM Role r WHERE r.id = :id AND r.deleted = false")
    Optional<Role> find(@Param("id") Long id);

    @Query("SELECT COUNT(r) FROM Role r WHERE r.deleted = false")
    Long total();
}

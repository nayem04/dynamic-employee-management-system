package com.dynamicemployeemanagementsystem.domain.repositories;

import com.dynamicemployeemanagementsystem.domain.entities.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT d FROM Department d WHERE (:query IS NULL OR d.name LIKE %:quer%) AND d.deleted = false")
    Page<Department> search(@Param("query") String query, Pageable pageable);

    @Query("SELECT d FROM Department d WHERE d.id = :id AND d.deleted = false")
    Optional<Department> find(@Param("id") long id);

    @Query("SELECT COUNT(d) FROM Department d WHERE d.deleted = false")
    Long total();
}

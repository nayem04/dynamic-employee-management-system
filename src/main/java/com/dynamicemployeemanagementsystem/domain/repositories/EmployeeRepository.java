package com.dynamicemployeemanagementsystem.domain.repositories;

import com.dynamicemployeemanagementsystem.domain.entities.Employee;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE (:query IS NULL OR e.user.firstName LIKE %:query% " +
            "OR e.user.lastName LIKE %:query%) AND e.deleted = false")
    Page<Employee> search(@Param("query") String query, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.id = :id AND e.deleted = false")
    Optional<Employee> find(@Param("id") Long id);

    @Query("SELECT e FROM Employee e WHERE e.deleted = false")
    Long total();
}

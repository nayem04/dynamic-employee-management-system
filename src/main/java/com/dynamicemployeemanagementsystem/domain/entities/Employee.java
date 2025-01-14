package com.dynamicemployeemanagementsystem.domain.entities;

import com.dynamicemployeemanagementsystem.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
public class Employee extends BaseEntity {
    @Column(name = "designation", nullable = false)
    private String designation;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "date_of_joining", nullable = false)
    private Date dateOfJoining;

    @Column(name = "salary", nullable = false)
    private double salary;

    @Column(name = "additional_attributes", columnDefinition = "jsonb")
    private String additionalAttributes; // Store as JSONB

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

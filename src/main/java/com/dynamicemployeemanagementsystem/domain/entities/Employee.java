package com.dynamicemployeemanagementsystem.domain.entities;

import com.dynamicemployeemanagementsystem.common.base.BaseEntity;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import java.util.Date;
import java.util.Map;

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

    /*Hibernate needs a type handler for Map<String, Object>.
    If you are using a JSON column (jsonb) in PostgreSQL,
    you can use the @Type annotation with a library like Hibernate Types.*/
    @Type(JsonType.class)
    @Column(name = "additional_attributes", columnDefinition = "jsonb")
    private Map<String, Object> additionalAttributes; // Store as JSONB

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

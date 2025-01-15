package com.dynamicemployeemanagementsystem.domain.dtos;

import com.dynamicemployeemanagementsystem.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@ToString
public class EmployeeDto extends BaseDto {
    @NotBlank(message = "Designation is required.")
    @JsonProperty(value = "designation", required = true)
    private String designation;

    @NotNull(message = "Date Of Joining is required.")
    @JsonProperty(value = "date_of_joining", required = true)
    private Date dateOfJoining;

    @NotNull(message = "Salary is required.")
    @JsonProperty(value = "salary", required = true)
    private double salary;

    @JsonProperty(value = "additional_attributes")
    private Map<String, Object> additionalAttributes; // Store as JSONB

    @NotNull(message = "Department Id is required.")
    @JsonProperty(value = "department_id", required = true)
    private Long departmentId;

    @NotNull(message = "User Id is required.")
    @JsonProperty(value = "user_id", required = true)
    private Long userId;
}

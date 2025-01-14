package com.dynamicemployeemanagementsystem.domain.dtos;

import com.dynamicemployeemanagementsystem.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleDto extends BaseDto {
    @NotBlank(message = "Label is required")
    @JsonProperty(value = "label", required = true)
    private String label;

    @NotBlank(message = "Name is required")
    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "description")
    private String description;
}

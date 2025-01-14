package com.dynamicemployeemanagementsystem.domain.dtos;

import com.dynamicemployeemanagementsystem.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserDto extends BaseDto {
    @NotBlank(message = "First Name is required")
    @JsonProperty(value = "first_name", required = true)
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    @NotBlank(message = "Username is required")
    @Size(min = 3, message = "Username length must be three digits or more")
    @JsonProperty(value = "username", required = true)
    private String username;

    @Email(message = "Email is invalid")
    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password length must be six digits or more")
    @JsonProperty(value = "password", required = true, access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(value = "role_ids")
    private List<Long> roleIds;

    @JsonProperty(value = "account_non_expired", required = true, access = JsonProperty.Access.READ_ONLY)
    private Boolean accountNonExpired = true;

    @JsonProperty(value = "account_non_locked", required = true, access = JsonProperty.Access.READ_ONLY)
    private Boolean accountNonLocked = true;

    @JsonProperty(value = "credentials_non_expired", required = true, access = JsonProperty.Access.READ_ONLY)
    private Boolean credentialsNonExpired = true;

    @JsonProperty(value = "enabled", required = true, access = JsonProperty.Access.READ_ONLY)
    private Boolean enabled = true;
}

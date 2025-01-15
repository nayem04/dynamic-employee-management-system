package com.dynamicemployeemanagementsystem.domain.dtos;

import com.dynamicemployeemanagementsystem.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
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

    @Pattern(message = "Invalid phone number.", regexp = "^(\\+8801|01)[3-9][0-9]{8}$")
    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password length must be six digits or more")
    @JsonProperty(value = "password", required = true, access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(value = "role_ids")
    private List<Long> roleIds;

    @NotNull(message = "Account Non Expired is required.")
    @JsonProperty(value = "account_non_expired", required = true, access = JsonProperty.Access.READ_ONLY)
    private Boolean accountNonExpired = true;

    @NotNull(message = "Account Non Locked is required.")
    @JsonProperty(value = "account_non_locked", required = true, access = JsonProperty.Access.READ_ONLY)
    private Boolean accountNonLocked = true;

    @NotNull(message = "Credentials Non Expired is required.")
    @JsonProperty(value = "credentials_non_expired", required = true, access = JsonProperty.Access.READ_ONLY)
    private Boolean credentialsNonExpired = true;

    @NotNull(message = "Enabled is required.")
    @JsonProperty(value = "enabled", required = true, access = JsonProperty.Access.READ_ONLY)
    private Boolean enabled = true;
}

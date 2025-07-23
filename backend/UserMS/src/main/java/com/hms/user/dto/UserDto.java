package com.hms.user.dto;

import com.hms.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// This class is a placeholder for User Data Transfer Object (DTO) implementation.
@Data
@NoArgsConstructor
@AllArgsConstructor
// Represents a user in the system.
// This class is used to transfer user data between layers, such as from the service layer to the controller layer.
// used for to handle api requests and responses.
public class UserDto {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    @NotBlank(message = "Password is required")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,15}$",
//            message = "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, and one digit")
    private String password;
    private Roles role;
    private Long profileId;

    public User toEntity(){
        return new User(this.id, this.name, this.email, this.password, this.role, this.profileId);
    }

}

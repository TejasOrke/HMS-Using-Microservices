package com.hms.user.entity;


import com.hms.user.dto.Roles;
import com.hms.user.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
// Represents a user in the system.
// This class is used to map the User entity to the database.
// it is ued to map the user table in the database.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private Roles role;
    private Long profileId;

    public UserDto toDto() {
        return new UserDto(this.id, this.name, this.email, this.password, this.role, this.profileId);
    }
}

package nl.novi.autogarage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nl.novi.autogarage.enumeration.UserRole;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;
    @Column(name = "user_role")
    private UserRole userRole;

    public User() {
    }

    public UserRole getUserRole() {
        return this.userRole;
    }
}

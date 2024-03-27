package nl.novi.autogarage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nl.novi.autogarage.enumeration.UserRole;
import org.springframework.context.annotation.Profile;

import java.util.HashSet;
import java.util.List;
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

    public User(String username, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;

    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

//    public UserRole getUserRole() {
//        return this.userRoles;
//    }
}

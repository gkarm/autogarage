package nl.novi.autogarage.dto;

import lombok.Getter;
import lombok.Setter;
import nl.novi.autogarage.enumeration.UserRole;

import java.util.List;

@Getter
@Setter
public class UserDto {

    public String username;

    public String password;
    public UserRole userRole;

//    public String[] roles;
}

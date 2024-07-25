package nl.novi.autogarage.dto;

import lombok.Getter;
import lombok.Setter;
import nl.novi.autogarage.enumeration.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter

public class UserDto {

    public String username;

    public String password;

    private List<UserRole> userRole;





}

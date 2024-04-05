package nl.novi.autogarage.service;

import nl.novi.autogarage.Security.MyUserDetails;
import nl.novi.autogarage.dto.UserDto;
import nl.novi.autogarage.dto.UserInputDto;
import nl.novi.autogarage.enumeration.UserRole;
import nl.novi.autogarage.exception.ForbiddenException;
import nl.novi.autogarage.exception.RecordNotFoundException;
import nl.novi.autogarage.model.User;
import nl.novi.autogarage.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

//    @Autowired

    private final UserRepository userRepos;


    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepos, PasswordEncoder passwordEncoder) {
        this.userRepos = userRepos;

        this.passwordEncoder = passwordEncoder;
    }

    public List<String> getAllUsers(){
        List<User> userList = userRepos.findAll();
        List<String> usernamesList = new ArrayList<>();
        for(User user : userList)
        {
            usernamesList.add(user.getUsername());
        }
        return usernamesList;
    }

    public UserDto getUser(MyUserDetails myUserDetails, String username){
        UserDto result;
        if (!username.equals(myUserDetails.getUsername()) &&
                !myUserDetails.getUserRole().contains(UserRole.ADMIN)){
            throw new ForbiddenException("You are logged in as "+myUserDetails.getUsername()+", not as "+username+".");
//                !myUserDetails.getUserRole().equals(UserRole.ADMIN)){
//            throw new ForbiddenException("You are logged in as "+myUserDetails.getUsername()+", not as "+username+".");
        }
        result= dtoFromUser(userFromName(username));

        return result;
    }

    public UserDto createUser(UserInputDto dto){
        Optional<User> userOptional = userRepos.findById(dto.getUsername());
        if (userOptional.isPresent())
        {
            throw new ForbiddenException("The username "+dto.getUsername()+" is no longer available, pick a different username.");
        }
        User user = userFromDto(dto);
        userRepos.save(user);

//        userRepos.save(user);


        return dtoFromUser(user);
    }

    public UserDto editUser(MyUserDetails myUserDetails, String username, UserInputDto dto){
        if (!myUserDetails.getUsername().equals(username) &&
                !myUserDetails.getUserRole().contains(UserRole.ADMIN)){
            throw new ForbiddenException("You are logged in as "+myUserDetails.getUsername()+", not as "+username+".");
        }
        User oldUser = userFromName(username);
        User newUser = userFromDto(dto);
        if (!newUser.getUsername().equals(oldUser.getUsername())){
            throw new RecordNotFoundException("Username cannot be changed, this is your ID");
        }

        userRepos.save(newUser);
        return dtoFromUser(newUser);
    }

    public void deleteUser(MyUserDetails myUserDetails, String username){
        Optional<User> userOptional = userRepos.findById(username);
        if (userOptional.isEmpty() || (!myUserDetails.getUsername().equals(username) &&
                !myUserDetails.getUserRole().contains(UserRole.ADMIN))){
            throw new ForbiddenException("You are logged in as "+myUserDetails.getUsername()+", not as "+username+".");
        }

        userRepos.deleteById(username);
    }



    public static List<UserRole> userFromName(List<String> stringList){

        List<UserRole>  result = new ArrayList<>();
        for (String string : stringList){
            result.add(UserRole.valueOf(string));
        }
        return result;
    }

    public User userFromName(String username){
        User result;
        Optional<User> userOptional = userRepos.findById(username);
        if (userOptional.isPresent())
        {
            result = userOptional.get();
        } else{
            throw new RecordNotFoundException("User "+username+" could not be found in the database");
        }
        return result;
    }

    public UserDto dtoFromUser(User user) {
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setUserRole(user.getUserRole());

        return dto;
    }

    public User userFromDto (UserInputDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
//        user.setUserRole(userFromName(dto.getRole()));
       user.setUserRole(userFromName(dto.getRole()));
        return user;
    }
}

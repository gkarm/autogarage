package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}

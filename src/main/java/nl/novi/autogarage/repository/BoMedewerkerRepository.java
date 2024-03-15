package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.BoMedewerker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoMedewerkerRepository extends JpaRepository<BoMedewerker, Long> {

}

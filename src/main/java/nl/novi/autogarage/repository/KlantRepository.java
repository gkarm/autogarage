package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.Klant;
import nl.novi.autogarage.model.Monteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface KlantRepository extends JpaRepository<Klant, Long> {


}

package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Auto, Long> {

}

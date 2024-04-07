package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.Tekortkoming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TekortkomingRepository extends JpaRepository<Tekortkoming, Long> {

}

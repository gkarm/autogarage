package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.Bon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonRepository extends JpaRepository<Bon, Long> {
}

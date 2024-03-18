package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.Keuring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeuringRepository extends JpaRepository<Keuring, Long> {

    void deleteById(Long id);
}

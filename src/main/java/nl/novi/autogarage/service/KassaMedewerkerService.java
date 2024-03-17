package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.KassaMedewerkerDto;
import nl.novi.autogarage.dto.MonteurDto;
import nl.novi.autogarage.model.KassaMedewerker;
import nl.novi.autogarage.model.Monteur;
import nl.novi.autogarage.repository.KassaMedewerkerRepository;
import org.springframework.stereotype.Service;

@Service
public class KassaMedewerkerService {

    private final KassaMedewerkerRepository repos;

    public KassaMedewerkerService(KassaMedewerkerRepository repos) {
        this.repos = repos;
    }
    public KassaMedewerkerDto createKassaMedewerker(KassaMedewerkerDto kassaMedewerkerDto) {
        KassaMedewerker kassaMedewerker = new KassaMedewerker();
        kassaMedewerker.setFirstName(kassaMedewerkerDto.firstName);
        kassaMedewerker.setLastName(kassaMedewerkerDto.lastName);
        kassaMedewerker.setDob(kassaMedewerkerDto.dob);
        repos.save(kassaMedewerker);
        kassaMedewerkerDto.id = kassaMedewerker.getId();

        return kassaMedewerkerDto;
    }
}

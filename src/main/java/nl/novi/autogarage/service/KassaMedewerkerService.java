package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.KassaMedewerkerDto;
import nl.novi.autogarage.dto.MonteurDto;
import nl.novi.autogarage.model.KassaMedewerker;
import nl.novi.autogarage.model.Monteur;
import nl.novi.autogarage.repository.KassaMedewerkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KassaMedewerkerService {
    @Autowired

    private final KassaMedewerkerRepository repos;

    public List<KassaMedewerker> getAllKassamedewerkers() {
        return repos.findAll();
    }

    public KassaMedewerker getKassamedewerkerById(Long id) {
        return repos.findById(id).orElse(null);
    }

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
    public KassaMedewerker updateKassamedewerker(Long id, KassaMedewerker kassaMedewerker) {
        if (repos.existsById(id)) {
            kassaMedewerker.setId(id);
            return repos.save(kassaMedewerker);
        }
        return null;
    }

    public void deleteKassamedewerker(Long id) {
        repos.deleteById(id);
    }
}

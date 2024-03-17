package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.KlantDto;
import nl.novi.autogarage.model.Klant;
import nl.novi.autogarage.repository.KlantRepository;
import org.springframework.stereotype.Service;

@Service
public class KlantService {

    private final KlantRepository repos;

    public KlantService(KlantRepository repos) {
        this.repos = repos;
    }

    public KlantDto createKlant(KlantDto klantDto) {
        Klant klant = new Klant();
        klant.setFirstName(klantDto.firstName);
        klant.setLastName(klantDto.lastName);
        klant.setPhone(klantDto.phone);
        klant.setDob(klantDto.dob);
        repos.save(klant);
        klantDto.id = klant.getId();

        return klantDto;

    }

}

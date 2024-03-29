package nl.novi.autogarage.service;


import nl.novi.autogarage.dto.MonteurDto;
import nl.novi.autogarage.model.Monteur;
import nl.novi.autogarage.repository.MonteurRepository;
import org.springframework.stereotype.Service;

@Service
public class MonteurService {

    private final MonteurRepository repos;
// Above code is the constructor injection
    public MonteurService(MonteurRepository repos) {
        this.repos = repos;
    }

    public MonteurDto createMonteur(MonteurDto monteurDto) {
        Monteur monteur = new Monteur();
        monteur.setFirstName(monteurDto.firstName);
        monteur.setLastName(monteurDto.lastName);
        monteur.setDob(monteurDto.dob);
        repos.save(monteur);
        monteurDto.id = monteur.getId();

        return monteurDto;
    }
}

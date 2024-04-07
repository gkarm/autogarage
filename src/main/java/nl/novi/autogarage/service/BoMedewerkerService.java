package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.BoMedewerkerDto;
import nl.novi.autogarage.model.BoMedewerker;
import nl.novi.autogarage.repository.BoMedewerkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoMedewerkerService {
    @Autowired
    private final BoMedewerkerRepository repos;

    public BoMedewerkerService(BoMedewerkerRepository repos)
    {this.repos = repos; }

    public List<BoMedewerker> getAllBoMedewerkers() {
        return repos.findAll();
    }

    public BoMedewerker getBoMedewerkerById(Long id) {
        return repos.findById(id).orElse(null);
    }
    public BoMedewerkerDto createBoMedewerker(BoMedewerkerDto boMedewerkerDto) {
        BoMedewerker boMedewerker = new BoMedewerker();
        boMedewerker.setFirstName(boMedewerkerDto.firstName);
        boMedewerker.setLastName(boMedewerkerDto.lastName);
        boMedewerker.setDob(boMedewerkerDto.dob);
        repos.save(boMedewerker);
        boMedewerkerDto.id = boMedewerker.getId();

        return boMedewerkerDto;
    }

    public BoMedewerker updateBoMedewerker(Long id, BoMedewerker boMedewerker) {
        if (repos.existsById(id)) {
            boMedewerker.setId(id);
            return repos.save(boMedewerker);
        }
        return null;
    }

    public void deleteBoMedewerker(Long id) {
        repos.deleteById(id);
    }
}

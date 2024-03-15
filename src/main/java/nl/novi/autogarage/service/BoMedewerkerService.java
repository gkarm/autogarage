package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.BoMedewerkerDto;
import nl.novi.autogarage.model.BoMedewerker;
import nl.novi.autogarage.repository.BoMedewerkerRepository;
import org.springframework.stereotype.Service;

@Service
public class BoMedewerkerService {
    private final BoMedewerkerRepository repos;

    public BoMedewerkerService(BoMedewerkerRepository repos)
    {this.repos = repos; }
    public BoMedewerkerDto createBoMedewerker(BoMedewerkerDto boMedewerkerDto) {
        BoMedewerker boMedewerker = new BoMedewerker();
        boMedewerker.setFirstName(boMedewerkerDto.firstName);
        boMedewerker.setLastName(boMedewerkerDto.lastName);
        boMedewerker.setDob(boMedewerkerDto.dob);
        repos.save(boMedewerker);
        boMedewerkerDto.id = boMedewerker.getId();

        return boMedewerkerDto;
    }
}

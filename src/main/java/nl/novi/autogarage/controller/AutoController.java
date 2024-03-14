package nl.novi.autogarage.controller;


import jakarta.validation.Valid;
import nl.novi.autogarage.dto.AutoDto;
import nl.novi.autogarage.model.Auto;
import nl.novi.autogarage.model.Monteur;
import nl.novi.autogarage.repository.AutoRepository;
import nl.novi.autogarage.repository.MonteurRepository;
import nl.novi.autogarage.service.AutoService;
import nl.novi.autogarage.service.MonteurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/autos")
public class AutoController {


    private final AutoRepository autoRepos;
    private final MonteurRepository monteurRepos;

    public AutoController(AutoRepository autoRepository, MonteurRepository monteurRepository) {
        this.autoRepos = autoRepository;
        this.monteurRepos = monteurRepository;

    }


    @PostMapping
    public ResponseEntity<AutoDto> createAuto(@Valid @RequestBody AutoDto autoDto, BindingResult br) {
        Auto auto = new Auto();
        auto.setModel(autoDto.model);
        auto.setMerk(autoDto.merk);
        auto.setKenteken(autoDto.kenteken);
        auto.setBouwjaar(autoDto.bouwjaar);
        for (Long id : autoDto.monteurIds) {
            Optional<Monteur> om = monteurRepos.findById(id);
            if (om.isPresent()) {
                Monteur monteur = om.get();
                auto.getMonteurs().add(monteur);
            }

        }
        autoRepos.save(auto);
        autoDto.id = auto.getId();
        return new ResponseEntity<>(autoDto, HttpStatus.CREATED);

    }

}

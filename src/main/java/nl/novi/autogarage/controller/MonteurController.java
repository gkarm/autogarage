package nl.novi.autogarage.controller;

import nl.novi.autogarage.model.Monteur;
import nl.novi.autogarage.repository.MonteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/monteurs")
public class MonteurController {

    @Autowired
    private MonteurRepository monteurRepository;


    @GetMapping
    public ResponseEntity<List<Monteur>> getAllMonteurs() {
        return ResponseEntity.ok(monteurRepository.findAll());

    }

    @GetMapping("/after")
    public ResponseEntity<List<Monteur>> getMonteursAfter(@RequestParam LocalDate date) {
        return ResponseEntity.ok(monteurRepository.findByDobAfter(date));
    }



    @PostMapping
    public ResponseEntity<Monteur> createMonteur(@RequestBody Monteur monteur) {
        monteurRepository.save(monteur);
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + monteur.getId()).toUriString());

        return ResponseEntity.created(uri).body(monteur);

    }

}

package nl.novi.autogarage.controller;


import jakarta.validation.Valid;
import nl.novi.autogarage.dto.AutoDto;
import nl.novi.autogarage.model.AdMedewerker;
import nl.novi.autogarage.model.Auto;
import nl.novi.autogarage.model.Monteur;
import nl.novi.autogarage.model.Tekortkoming;
import nl.novi.autogarage.repository.AutoRepository;
import nl.novi.autogarage.repository.MonteurRepository;
import nl.novi.autogarage.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autos")
public class AutoController {
    @Autowired
    private final AutoRepository autoRepos;
    private final MonteurRepository monteurRepos;
    private AutoService autoService;

    public AutoController(AutoRepository autoRepository, MonteurRepository monteurRepository, AutoService autoService) {
        this.autoRepos = autoRepository;
        this.monteurRepos = monteurRepository;


        this.autoService = autoService;
    }



    @GetMapping
    public List<Auto> getAllAuto() {
        return autoService.getAllAuto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auto> getAutoById(@PathVariable Long id) {
        Auto auto = autoService.getAutoById(id);
        if (auto != null) {
            return ResponseEntity.ok(auto);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<?> createAuto(@Valid @RequestBody AutoDto autoDto, BindingResult br) {

        if (br.hasErrors()) {
            return ResponseEntity.badRequest().body(br.getAllErrors());
        }
        AutoDto auto = autoService.createAuto(autoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(auto);


    }

    @PutMapping("/{id}")
    public ResponseEntity<Auto> updateAuto(@PathVariable Long id, @RequestBody Auto auto) {
        Auto updatedAuto = autoService.updateAuto(id, auto);
        if (updatedAuto != null) {
            return ResponseEntity.ok(updatedAuto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuto(@PathVariable Long id) {
        autoService.deleteAuto(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{autoId}/tekortkomingen")
    public ResponseEntity<Auto> addTekortkomingToAuto(@PathVariable Long autoId, @RequestBody Tekortkoming tekortkoming) {
        autoService.addTekortkomingToAuto(autoId, tekortkoming);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        /* hier monteur kan tekortkomingen toevoegen aan een auto
        eindpoint  /autos/{autoId}/tekortkomingen
        autoId is het ID van de auto*/
    }

}

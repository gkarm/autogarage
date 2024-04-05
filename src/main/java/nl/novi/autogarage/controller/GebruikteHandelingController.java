package nl.novi.autogarage.controller;

import nl.novi.autogarage.model.GebruikteHandeling;
import nl.novi.autogarage.service.GebruikteHandelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gebruikte-handelingen")
public class GebruikteHandelingController {

    @Autowired
    private GebruikteHandelingService gebruikteHandelingService;

    @PostMapping
    public ResponseEntity<GebruikteHandeling> createGebruikteHandeling(@RequestBody GebruikteHandeling gebruikteHandeling) {
        GebruikteHandeling createdGebruikteHandeling = gebruikteHandelingService.createGebruikteHandeling(gebruikteHandeling);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGebruikteHandeling);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GebruikteHandeling> getGebruikteHandelingById(@PathVariable Long id) {
        GebruikteHandeling gebruikteHandeling = gebruikteHandelingService.getGebruikteHandelingById(id);
        if (gebruikteHandeling != null) {
            return ResponseEntity.ok(gebruikteHandeling);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGebruikteHandeling(@PathVariable Long id) {
        gebruikteHandelingService.deleteGebruikteHandeling(id);
        return ResponseEntity.noContent().build();
    }
    
}

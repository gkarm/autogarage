package nl.novi.autogarage.controller;

import nl.novi.autogarage.model.Reparatie;
import nl.novi.autogarage.service.ReparatieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reparaties")
public class ReparatieController {
    private final ReparatieService service;

    @Autowired

    public ReparatieController(ReparatieService reparatieService) {
        this.service = reparatieService;
    }
    // Endpoint om alle reparaties op te halen
    @GetMapping
    public ResponseEntity<List<Reparatie>> getAllReparaties() {
        List<Reparatie> reparaties = service.getAllReparaties();
        return new ResponseEntity<>(reparaties, HttpStatus.OK);
    }
    // Endpoint om een specifieke reparatie op te halen op basis van ID
    @GetMapping("/{id}")
    public ResponseEntity<Reparatie> getReparatieById(@PathVariable Long id) {
        Reparatie reparatie = service.getReparatieById(id);
        if (reparatie != null) {
            return new ResponseEntity<>(reparatie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Endpoint om een nieuwe reparatie toe te voegen
    @PostMapping
    public ResponseEntity<Reparatie> addReparatie(@RequestBody Reparatie reparatie) {
        Reparatie addedReparatie = service.addReparatie(reparatie);
        return new ResponseEntity<>(addedReparatie, HttpStatus.CREATED);
    }

    // Endpoint om een reparatie bij te werken
    @PutMapping("/{id}")
    public ResponseEntity<Reparatie> updateReparatie(@PathVariable Long id, @RequestBody Reparatie updatedReparatie) {
        Reparatie reparatie = service.updateReparatie(id, updatedReparatie);
        if (reparatie != null) {
            return new ResponseEntity<>(reparatie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint om een reparatie te verwijderen op basis van ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReparatie(@PathVariable Long id) {
        service.deleteReparatie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}

package nl.novi.autogarage.controller;

import nl.novi.autogarage.model.Onderdeel;
import nl.novi.autogarage.service.OnderdeelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/onderdelen")
public class OnderdeelController {
    private final OnderdeelService service;

    @Autowired
public OnderdeelController(OnderdeelService onderdeelService) {
    this.service = onderdeelService;
}
    // Endpoint om alle onderdelen op te halen
@GetMapping
    public ResponseEntity<List<Onderdeel>> getAllOnderdelen() {
    List<Onderdeel> onderdelen = service.getAllOnderdelen();
    return new ResponseEntity<>(onderdelen, HttpStatus.OK);

}
    // Endpoint om een specifiek onderdeel op te halen op basis van ID
    @GetMapping("/{id}")
    public ResponseEntity<Onderdeel> getOnderdeelById(@PathVariable Long id) {
        Onderdeel onderdeel = service.getOnderdeelById(id);
        if (onderdeel != null) {
            return new ResponseEntity<>(onderdeel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Endpoint om een nieuw onderdeel toe te voegen
    @PostMapping
    public ResponseEntity<Onderdeel> addOnderdeel(@RequestBody Onderdeel onderdeel) {
        Onderdeel addedOnderdeel = service.addOnderdeel(onderdeel);
        return new ResponseEntity<>(addedOnderdeel, HttpStatus.CREATED);
    }

    // Endpoint om een onderdeel bij te werken

    @PutMapping("/{id}")
    public ResponseEntity<Onderdeel> updateOnderdeel(@PathVariable Long id, @RequestBody Onderdeel updatedOnderdeel) {
        Onderdeel onderdeel = service.updateOnderdeel(id, updatedOnderdeel);
        if (onderdeel != null) {
            return new ResponseEntity<>(onderdeel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint om een onderdeel te verwijderen op basis van ID

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnderdeel(@PathVariable Long id) {
        service.deleteOnderdeel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

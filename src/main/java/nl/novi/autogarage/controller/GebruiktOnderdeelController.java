package nl.novi.autogarage.controller;

import nl.novi.autogarage.model.GebruiktOnderdeel;
import nl.novi.autogarage.service.GebruiktOnderdeelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gebruikt-onderdelen")
public class GebruiktOnderdeelController {
    @Autowired
    private GebruiktOnderdeelService gebruiktOnderdeelService;

    @PostMapping
    public ResponseEntity<GebruiktOnderdeel> createGebruiktOnderdeel(@RequestBody GebruiktOnderdeel gebruiktOnderdeel) {
        GebruiktOnderdeel createdGebruiktOnderdeel = gebruiktOnderdeelService.createGebruiktOnderdeel(gebruiktOnderdeel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGebruiktOnderdeel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GebruiktOnderdeel> getGebruiktOnderdeelById(@PathVariable Long id) {
        GebruiktOnderdeel gebruiktOnderdeel = gebruiktOnderdeelService.getGebruiktOnderdeelById(id);
        if (gebruiktOnderdeel != null) {
            return ResponseEntity.ok(gebruiktOnderdeel);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGebruiktOnderdeel(@PathVariable Long id) {
        gebruiktOnderdeelService.deleteGebruiktOnderdeel(id);
        return ResponseEntity.noContent().build();
    }
}

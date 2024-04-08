package nl.novi.autogarage.controller;

import nl.novi.autogarage.dto.TekortkomingDto;
import nl.novi.autogarage.model.Auto;
import nl.novi.autogarage.model.Tekortkoming;
import nl.novi.autogarage.service.TekortkomingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tekortkomingen")
public class TekortkomingController {
    @Autowired
    private TekortkomingService tekortkomingService;
    private Auto auto;

    @PostMapping
    public ResponseEntity<Tekortkoming> createTekortkoming(@RequestBody TekortkomingDto tekortkomingDto) {
        Tekortkoming createdTekortkoming = tekortkomingService.createTekortkoming(mapToTekortkoming(tekortkomingDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTekortkoming);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tekortkoming> getTekortkomingById(@PathVariable Long id) {
        Tekortkoming tekortkoming = tekortkomingService.getTekortkomingById(id);
        if (tekortkoming != null) {
            return ResponseEntity.ok(tekortkoming);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tekortkoming> updateTekortkoming(@PathVariable Long id, @RequestBody TekortkomingDto tekortkomingDto) {
        Tekortkoming updatedTekortkoming = tekortkomingService.updateTekortkoming(id, tekortkomingDto);
        if (updatedTekortkoming != null) {
            return ResponseEntity.ok(updatedTekortkoming);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTekortkoming(@PathVariable Long id) {
        tekortkomingService.deleteTekortkoming(id);
        return ResponseEntity.noContent().build();
    }

    private Tekortkoming mapToTekortkoming(TekortkomingDto tekortkomingDto) {
        Tekortkoming tekortkoming = new Tekortkoming(auto);
        tekortkoming.setBeschrijving(tekortkomingDto.getBeschrijving());
        tekortkoming.setOplossing(tekortkomingDto.getOplossing());
        return tekortkoming;
    }
}

package nl.novi.autogarage.controller;

import nl.novi.autogarage.model.Monteur;
import nl.novi.autogarage.repository.MonteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

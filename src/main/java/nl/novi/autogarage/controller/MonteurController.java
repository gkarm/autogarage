package nl.novi.autogarage.controller;

import jakarta.validation.Valid;
import nl.novi.autogarage.dto.MonteurDto;
import nl.novi.autogarage.service.MonteurService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/monteurs")
public class MonteurController {

    private final MonteurService service;

    public MonteurController(MonteurService service) {
        this.service = service;
    }




//    @GetMapping
//    public ResponseEntity<List<Monteur>> getAllMonteurs() {
//        return ResponseEntity.ok(monteurRepository.findAll());
//
//    }
//
//    @GetMapping("/after")
//    public ResponseEntity<List<Monteur>> getMonteursAfter(@RequestParam LocalDate date) {
//        return ResponseEntity.ok(monteurRepository.findByDobAfter(date));
//    }



    @PostMapping
    public ResponseEntity<Object> createMonteur(@Valid @RequestBody MonteurDto monteurDto, BindingResult br) {

        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");

            }
            return ResponseEntity.badRequest().body(sb.toString());

        }
        else {
            monteurDto = service.createMonteur(monteurDto);

            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + monteurDto.id).toUriString());

            return ResponseEntity.created(uri).body(monteurDto);
        }

    }


}

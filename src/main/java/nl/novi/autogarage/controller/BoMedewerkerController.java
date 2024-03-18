package nl.novi.autogarage.controller;


import jakarta.validation.Valid;
import nl.novi.autogarage.dto.BoMedewerkerDto;
import nl.novi.autogarage.service.BoMedewerkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/bomedewerkers")
public class BoMedewerkerController {

    private final BoMedewerkerService service;

    public BoMedewerkerController(BoMedewerkerService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Object> createBoMedewerker(@Valid @RequestBody BoMedewerkerDto boMedewerkerDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());

        } else {
            boMedewerkerDto = service.createBoMedewerker(boMedewerkerDto);

            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + boMedewerkerDto.id).toUriString());
            return ResponseEntity.created(uri).body(boMedewerkerDto);
        }

    }
}

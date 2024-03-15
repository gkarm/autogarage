package nl.novi.autogarage.controller;

import jakarta.validation.Valid;
import nl.novi.autogarage.dto.AdMedewerkerDto;
import nl.novi.autogarage.model.AdMedewerker;
import nl.novi.autogarage.model.Auto;
import nl.novi.autogarage.repository.AdMedewerkerRepository;
import nl.novi.autogarage.repository.AutoRepository;
import nl.novi.autogarage.service.AdMedewerkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admedewerkers")

public class AdMedewerkerController {


    private final AdMedewerkerService service;

    public AdMedewerkerController(AdMedewerkerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> createAdMedewerker(@Valid @RequestBody AdMedewerkerDto adMedewerkerDto, BindingResult br) {
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
            adMedewerkerDto = service.createAdMedewerker(adMedewerkerDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + adMedewerkerDto.id).toUriString());
            return ResponseEntity.created(uri).body(adMedewerkerDto);
        }
    }
}

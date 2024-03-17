package nl.novi.autogarage.controller;

import jakarta.validation.Valid;
import nl.novi.autogarage.dto.KlantDto;
import nl.novi.autogarage.service.KlantService;
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
@RequestMapping("/klanten")
public class KlantController {

    private final KlantService service;

    public KlantController(KlantService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Object> createKlant(@Valid @RequestBody KlantDto klantDto, BindingResult br) {
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
            klantDto = service.createKlant(klantDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + klantDto.id).toUriString());
            return ResponseEntity.created(uri).body(klantDto);
        }
    }
}

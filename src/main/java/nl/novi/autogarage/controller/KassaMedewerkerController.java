package nl.novi.autogarage.controller;

import jakarta.validation.Valid;
import nl.novi.autogarage.dto.KassaMedewerkerDto;
import nl.novi.autogarage.service.KassaMedewerkerService;
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
@RequestMapping("/kassamedewerkers")
public class KassaMedewerkerController {
    private final KassaMedewerkerService service;

    public KassaMedewerkerController(KassaMedewerkerService service) {
        this.service = service;

    }
    @PostMapping
    public ResponseEntity<Object> createKassaMedewerker(@Valid @RequestBody KassaMedewerkerDto kassaMedewerkerDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");

            }
            return ResponseEntity.badRequest().body(sb.toString());

        }else {
            kassaMedewerkerDto = service.createKassaMedewerker(kassaMedewerkerDto);

            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + kassaMedewerkerDto.id).toUriString());

            return ResponseEntity.created(uri).body(kassaMedewerkerDto);
        }
    }
}

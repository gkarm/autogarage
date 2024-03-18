package nl.novi.autogarage.dto;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import nl.novi.autogarage.model.Monteur;
import nl.novi.autogarage.model.Onderdeel;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReparatieDto {

    public Long id;
    public Monteur monteur;
    public Date datum;
    public List<Onderdeel> onderdelen;
}

package nl.novi.autogarage.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import nl.novi.autogarage.model.KassaMedewerker;
import nl.novi.autogarage.model.Monteur;

@Getter
@Setter
public class OnderdeelDto {

    public Long id;


    public Monteur monteur;

    public KassaMedewerker kassaMedewerker;

    public String OnderdeelName;
    public double prijs;
    public int voorraad;

}

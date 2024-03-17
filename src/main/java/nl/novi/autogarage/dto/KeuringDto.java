package nl.novi.autogarage.dto;
import lombok.*;
import nl.novi.autogarage.model.Auto;
import nl.novi.autogarage.model.Monteur;

import java.util.Date;
@Getter
@Setter

public class KeuringDto {
    public Long id;
    public Monteur monteur;

    public Auto auto;
    public Date datum;
    public String opmerking;
    public String status; // Status van de Keuring, bijv. "In behandeling", "Voltooid"
    public String keuringsResultaat;
}

package nl.novi.autogarage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import nl.novi.autogarage.model.AdMedewerker;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class AutoDto {
    @Getter
    public Long id;
    @Getter
    @NotBlank
    public String merk;
    @Getter
    @NotBlank
    public String model;
    @Getter
    @NotBlank
    public String kenteken;
    @Getter
    @Past
    @JsonFormat(pattern = "dd-MM-yyyy")
    public LocalDate bouwjaar;

    public List<Long> monteurIds; // public List<Long> monteurIds = Collections.emptyList();
    public List<Long> admedewerker;

    public List<Long> klantIds;

    @Getter
    public List<Long> kassamedewerker;
    @Getter
    public List<Long> bomedewerker;

    public void setKassamedewerker(List<Long> kassamedewerker) {
        this.kassamedewerker = kassamedewerker;
    }

    public void setBomedewerker(List<Long> bomedewerker) {
        this.bomedewerker = bomedewerker;
    }

    private List<Long> klant;

    public void setId(Long id) {
        this.id = id;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }

    public void setBouwjaar(LocalDate bouwjaar) {
        this.bouwjaar = bouwjaar;
    }

    public List<Long> getMonteur() {
        return monteurIds;
    }

    public void setMonteur(List<Long> monteurIds) {
        this.monteurIds = monteurIds;
    }

    public void getAdmedewerker(AdMedewerker adMedewerker) {
    }

    public void setAdmedewerker(List<Long> adMedewerker) {
        this.admedewerker = adMedewerker;
    }


    public void setKlantIds(List<Long> klantIds) {
        this.klantIds = klantIds;
    }

    public void setKlant(List<Long> klant) {
        this.klant = klant;
    }

//    public void setKlant(List<Long> klant) {
//        this.klant = klant;
//    }
}

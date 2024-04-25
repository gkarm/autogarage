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

//    public Long id;
//    public Monteur monteur;
//    public Date datum;
//    public List<Onderdeel> onderdelen;

    private Long id;
    private Monteur monteur;
    private Date datum;
    private String beschrijving;
    private double totaalBedrag;
    private String status;

    // In plaats van volledige objecten zoals Onderdeel, Bon, etc. te hebben,
    // kunt u bijvoorbeeld alleen hun ID's gebruiken om relaties te beheren
    private Long bonId;
    private List<Long> onderdelenIds;  // IDs van de onderdelen
    private List<Long> gebruikteOnderdeelIds;  // IDs van gebruikte onderdelen
    private List<Long> gebruikteHandelingIds;  // IDs van gebruikte handelingen




    public ReparatieDto(Long id, Monteur monteur, Date datum, String beschrijving, double totaalBedrag, String status, Long bonId, List<Long> onderdelenIds, List<Long> gebruikteOnderdeelIds, List<Long> gebruikteHandelingIds) {
        this.id = id;
        this.monteur = monteur;
        this.datum = datum;
        this.beschrijving = beschrijving;
        this.totaalBedrag = totaalBedrag;
        this.status = status;
        this.bonId = bonId;
        this.onderdelenIds = onderdelenIds;
        this.gebruikteOnderdeelIds = gebruikteOnderdeelIds;
        this.gebruikteHandelingIds = gebruikteHandelingIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Monteur getMonteur() {
        return monteur;
    }

    public void setMonteur(Monteur monteur) {
        this.monteur = monteur;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getTotaalBedrag() {
        return totaalBedrag;
    }

    public void setTotaalBedrag(double totaalBedrag) {
        this.totaalBedrag = totaalBedrag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getBonId() {
        return bonId;
    }

    public void setBonId(Long bonId) {
        this.bonId = bonId;
    }

    public List<Long> getOnderdelenIds() {
        return onderdelenIds;
    }

    public void setOnderdelenIds(List<Long> onderdelenIds) {
        this.onderdelenIds = onderdelenIds;
    }

    public List<Long> getGebruikteOnderdeelIds() {
        return gebruikteOnderdeelIds;
    }

    public void setGebruikteOnderdeelIds(List<Long> gebruikteOnderdeelIds) {
        this.gebruikteOnderdeelIds = gebruikteOnderdeelIds;
    }

    public List<Long> getGebruikteHandelingIds() {
        return gebruikteHandelingIds;
    }

    public void setGebruikteHandelingIds(List<Long> gebruikteHandelingIds) {
        this.gebruikteHandelingIds = gebruikteHandelingIds;
    }
}

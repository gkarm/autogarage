package nl.novi.autogarage.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.Getter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "autos")
public class Auto {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String merk;
    @Getter
    private String model;
    @Getter
    private String kenteken;
    private @Past LocalDate bouwjaar;

    @Getter
    @ManyToMany

    private Set<Monteur> monteurs = new HashSet<>();
    @Getter
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    private AdMedewerker adMedewerker;
    @Getter
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    private KassaMedewerker kassaMedewerker;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private BoMedewerker boMedewerker;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Klant klant;

    @Getter
    @OneToMany(mappedBy = "auto", cascade = CascadeType.ALL)
    private List<Tekortkoming> tekortkomingen;


    public void setMerk(String merk) {
        this.merk = merk;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }

    public @Past LocalDate getBouwjaar() {
        return bouwjaar;
    }

    public void setBouwjaar(@Past LocalDate bouwjaar) {
        this.bouwjaar = bouwjaar;
    }

    public void setId(Long id) {
    }

    public void setMonteurs(Set<Monteur> monteurs) {
        this.monteurs = monteurs;
    }

    public void setAdMedewerker(AdMedewerker adMedewerker) {
        this.adMedewerker = adMedewerker;
    }

    public void setKassaMedewerker(KassaMedewerker kassaMedewerker) {
        this.kassaMedewerker = kassaMedewerker;
    }

    public void setBoMedewerker(BoMedewerker boMedewerker) {
        this.boMedewerker = boMedewerker;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public void setTekortkomingen(List<Tekortkoming> tekortkomingen) {
        this.tekortkomingen = tekortkomingen;
    }
}

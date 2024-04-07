package nl.novi.autogarage.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "autos")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String merk;
    private String model;
    private String kenteken;
    private @Past LocalDate bouwjaar;

    @ManyToMany

    private Set<Monteur> monteurs = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    private AdMedewerker adMedewerker;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    private KassaMedewerker kassaMedewerker;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private BoMedewerker boMedewerker;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Klant klant;


    public Long getId() {
        return id;
    }
    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getKenteken() {
        return kenteken;
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

    public Set<Monteur> getMonteurs() {
        return monteurs;
    }

    public void setId(Long id) {
    }
}

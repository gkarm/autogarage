package nl.novi.autogarage.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reparaties")
public class Reparatie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @ManyToOne
    private Monteur monteur;
    private Date datum;
    @OneToMany
    private List<Onderdeel> onderdelen;

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

    public List<Onderdeel> getOnderdelen() {
        return onderdelen;
    }

    public void setOnderdelen(List<Onderdeel> onderdelen) {
        this.onderdelen = onderdelen;
    }
}

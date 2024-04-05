package nl.novi.autogarage.model;

import jakarta.persistence.*;

@Entity
public class GebruiktOnderdeel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "reparatie_id", nullable = false)
    private Reparatie reparatie;

    @ManyToOne
    @JoinColumn(name = "onderdeel_id", nullable = false)
    private Onderdeel onderdeel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reparatie getReparatie() {
        return reparatie;
    }

    public void setReparatie(Reparatie reparatie) {
        this.reparatie = reparatie;
    }

    public Onderdeel getOnderdeel() {
        return onderdeel;
    }

    public void setOnderdeel(Onderdeel onderdeel) {
        this.onderdeel = onderdeel;
    }
}

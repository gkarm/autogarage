package nl.novi.autogarage.model;

import jakarta.persistence.*;

@Entity
public class GebruikteHandeling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Toegevoegde velden
    @ManyToOne
    @JoinColumn(name = "reparatie_id", nullable = false)
    private Reparatie reparatie;

    @ManyToOne
    @JoinColumn(name = "handeling_id", nullable = false)
    private Handeling handeling;

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

    public Handeling getHandeling() {
        return handeling;
    }

    public void setHandeling(Handeling handeling) {
        this.handeling = handeling;
    }
}

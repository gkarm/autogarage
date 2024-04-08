package nl.novi.autogarage.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "tekortkomingen")
public class Tekortkoming {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String beschrijving;
    @Getter
    private String oplossing;

    @ManyToOne
    @JoinColumn(name = "auto_id", nullable = false)
    private Auto auto;

    public Tekortkoming(Auto auto) {
        this.auto = auto;
        this.beschrijving = beschrijving;
        this.oplossing = oplossing;
    }

    public Tekortkoming() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setOplossing(String oplossing) {
        this.oplossing = oplossing;
    }

    public void setAuto(Auto auto) {
    }
}

package nl.novi.autogarage.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tekortkomingen")
public class Tekortkoming {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String beschrijving;
    private String oplossing;

    public Tekortkoming() {
        this.id = id;
        this.beschrijving = beschrijving;
        this.oplossing = oplossing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public String getOplossing() {
        return oplossing;
    }

    public void setOplossing(String oplossing) {
        this.oplossing = oplossing;
    }
}

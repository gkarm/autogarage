package nl.novi.autogarage.model;

import jakarta.persistence.*;

@Entity
@Table(name = "onderdelen")
public class Onderdeel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "monteur_id")
//    private Monteur monteur;

//    @ManyToOne
//    @JoinColumn(name = "kassaMedewerker_id")
//    private KassaMedewerker kassaMedewerker;

    @Column(name = "onderdeel_name", length = 128)
    private String OnderdeelName;
    @Column(name = "prijs")
    private double prijs;
    @Column(name = "voorraad")
    private int voorraad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Monteur getMonteur() {
//        return monteur;
//    }

//    public void setMonteur(Monteur monteur) {
//        this.monteur = monteur;
//    }

//    public KassaMedewerker getKassaMedewerker() {
//        return kassaMedewerker;
//    }

//    public void setKassaMedewerker(KassaMedewerker kassaMedewerker) {
//        this.kassaMedewerker = kassaMedewerker;
//    }

    public String getOnderdeelName() {
        return OnderdeelName;
    }

    public void setOnderdeelName(String onderdeelName) {
        OnderdeelName = onderdeelName;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }
}

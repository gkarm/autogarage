package nl.novi.autogarage.dto;

public class GebruiktOnderdeelDto {
    private Long id;
    private Long reparatieId;
    private Long onderdeelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReparatieId() {
        return reparatieId;
    }

    public void setReparatieId(Long reparatieId) {
        this.reparatieId = reparatieId;
    }

    public Long getOnderdeelId() {
        return onderdeelId;
    }

    public void setOnderdeelId(Long onderdeelId) {
        this.onderdeelId = onderdeelId;
    }
}

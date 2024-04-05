package nl.novi.autogarage.dto;

public class GebruikteHandelingDto {
    public Long id;
    public Long reparatieId;
    public Long handelingId;

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

    public Long getHandelingId() {
        return handelingId;
    }

    public void setHandelingId(Long handelingId) {
        this.handelingId = handelingId;
    }
}

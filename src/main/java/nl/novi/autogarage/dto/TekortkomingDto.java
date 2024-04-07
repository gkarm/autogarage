package nl.novi.autogarage.dto;

public class TekortkomingDto {
    private String beschrijving;
    private String oplossing;

    public TekortkomingDto(String beschrijving, String oplossing) {
        this.beschrijving = beschrijving;
        this.oplossing = oplossing;
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

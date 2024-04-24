package nl.novi.autogarage.dto;

import lombok.Getter;
import nl.novi.autogarage.model.Tekortkoming;
import nl.novi.autogarage.repository.AutoRepository;

public class TekortkomingDto extends Tekortkoming {


    private final AutoRepository autoRepository;
    @Getter
    private Long id;
    @Getter
    private Long autoId;


    @Getter
    private String beschrijving;
    @Getter
    private String oplossing;

    public TekortkomingDto(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }


    public TekortkomingDto(AutoRepository autoRepository, String beschrijving, String oplossing, Long autoId) {
        this.autoRepository = autoRepository;
        this.beschrijving = beschrijving;
        this.oplossing = oplossing;
        this.autoId = autoId;

    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setOplossing(String oplossing) {
        this.oplossing = oplossing;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public AutoRepository getAutoRepository() {
        return autoRepository;
    }

    public Long getId() {
        return id;
    }

    public Long getAutoId() {
        return autoId;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public String getOplossing() {
        return oplossing;
    }
}

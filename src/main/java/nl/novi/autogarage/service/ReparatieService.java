package nl.novi.autogarage.service;

import nl.novi.autogarage.model.*;
import nl.novi.autogarage.repository.GebruiktOnderdeelRepository;
import nl.novi.autogarage.repository.GebruikteHandelingRepository;
import nl.novi.autogarage.repository.ReparatieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ReparatieService {
    @Autowired
    private final ReparatieRepository repos;
    @Autowired
    private GebruiktOnderdeelService gebruiktOnderdeelService;
    @Autowired
    private GebruikteHandelingService gebruikteHandelingService;
    @Autowired
    private HandelingService handelingService;
    @Autowired
    private BonService bonService;
    @Autowired
    private OnderdeelService onderdeelService;
    @Autowired
    private GebruiktOnderdeelRepository gebruiktOnderdeelRepository;
    @Autowired
    private GebruikteHandelingRepository gebruikteHandelingRepository;

    @Autowired

    public ReparatieService(ReparatieRepository repos) {
        this.repos = repos;
    }
//    Methode om alle reparaties op te halen

//    @Autowired
//    private GebruiktOnderdeelService gebruiktOnderdeelService;
//
//    @Autowired
//    private GebruikteHandelingService gebruikteHandelingService;

    public List<Reparatie> getAllReparaties() {
        return repos.findAll();
    }
// Methode om een specifieke reparatie op te halen op basis van ID
    public Reparatie getReparatieById(Long id) {
        return repos.findById(id).orElse(null);
    }

// Methode om een nieuwe reparatie toe te voegen
public Reparatie addReparatie(Reparatie reparatie) {
    return repos.save(reparatie);
}
// Methode om een reparatie bij te werken
public Reparatie updateReparatie(Long id, Reparatie updatedReparatie) {
    if (repos.existsById(id)) {
        updatedReparatie.setId(id);
        return repos.save(updatedReparatie);
    }
    return null; //
}

// Methode om een reparatie te verwijderen op basis van ID
public void deleteReparatie(Long id) {
    repos.deleteById(id);
}

    public void setReparatieNietUitvoeren(Long reparatieId) {
        Reparatie reparatie = repos.findById(reparatieId).orElse(null);
        if (reparatie != null) {
            reparatie.setStatus("niet uitvoeren");
//            Bon maken voor keuring
            Bon bon = bonService.createBon(45.0);
            reparatie.setBon(bon);

            repos.save(reparatie);
        }
    }

    public void koppelBonAanReparatie(Long reparatieId, Long bonId) {
        Reparatie reparatie = repos.findById(reparatieId).orElse(null);
        Bon bon = bonService.getBonById(bonId);

        if (reparatie != null && bon != null) {
            reparatie.setBon(bon);
            repos.save(reparatie);
        }
    }

    public void voegGebruiktOnderdeelToeAanReparatie(Long reparatieId, Long onderdeelId) {
        Reparatie reparatie = repos.findById(reparatieId).orElse(null);
        Onderdeel onderdeel = onderdeelService.getOnderdeelById(onderdeelId);

        if (reparatie != null && onderdeel != null) {
            GebruiktOnderdeel gebruiktOnderdeel = new GebruiktOnderdeel();
            gebruiktOnderdeel.setReparatie(reparatie);
            gebruiktOnderdeel.setOnderdeel(onderdeel);
            gebruiktOnderdeelRepository.save(gebruiktOnderdeel);
//            GebruiktOnderdeel GebruiktOnderdeel = null;
//            gebruiktOnderdeelService.createGebruiktOnderdeel(GebruiktOnderdeel);
        }
    }
    public void voegGebruikteHandelingToeAanReparatie(Long reparatieId, Long handelingId) {
        Reparatie reparatie = repos.findById(reparatieId).orElse(null);
//        Onderdeel onderdeel = onderdeelService.getOnderdeelById(onderdeelId);
        Handeling handeling = handelingService.getHandelingById(handelingId);

        if (reparatie != null && handeling != null) {
            GebruikteHandeling gebruikteHandeling = new GebruikteHandeling();
            gebruikteHandeling.setReparatie(reparatie);
            gebruikteHandeling.setHandeling(handeling);
            gebruikteHandelingRepository.save(gebruikteHandeling);
//            gebruiktOnderdeelService.createGebruiktOnderdeel(reparatie, handeling);
//            gebruikteHandelingService.createGebruikteHandeling(reparatie, handeling);
        }
    }

    public void voegOnderdeelEnHandelingToeAanReparatie(Long reparatieId, Long onderdeelId, Long handelingId) {
        Reparatie reparatie = repos.findById(reparatieId).orElse(null);
        Onderdeel onderdeel = onderdeelService.getOnderdeelById(onderdeelId);
        Handeling handeling = handelingService.getHandelingById(handelingId);

        if (reparatie != null && onderdeel != null && handeling != null) {
            // Voeg gebruikte onderdeel toe aan de bon
            gebruiktOnderdeelService.createGebruiktOnderdeel(reparatie, onderdeel);

            // Voeg gebruikte handeling toe aan de bon
            gebruikteHandelingService.createGebruikteHandeling(reparatie, handeling);
        }
    }


}

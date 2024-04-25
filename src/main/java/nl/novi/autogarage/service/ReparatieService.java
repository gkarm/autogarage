package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.BonDto;
import nl.novi.autogarage.exception.ResourceNotFoundException;
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

    public ReparatieService(ReparatieRepository repos, BonService bonService) {

        this.repos = repos;
        this.bonService = bonService;
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

        Bon bon = bonService.createBon(new BonDto(0L, 00));
        reparatie.setBon(bon);

        return repos.save(reparatie);
}
// Methode om een reparatie bij te werken

    public Reparatie updateReparatie(Long id, Reparatie updatedReparatie) {
        Reparatie existingReparatie = repos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reparatie not found with id " + id));

        // Reparatiegegevens bijwerken
        existingReparatie.setBeschrijving(updatedReparatie.getBeschrijving());
        existingReparatie.setTotaalBedrag(updatedReparatie.getTotaalBedrag()); // Moet nog berekend worden

        // Bon bijwerken (indien nodig)
        if (existingReparatie.getBon() != null) {
            // Bereken het totaalbedrag voor de bon
            double totaalBedrag = berekenTotaalBedrag(existingReparatie);
            // Update het totaalbedrag van de bon
            existingReparatie.getBon().setBedrag(totaalBedrag);
            // Update de bon in de database
            bonService.updateBon(existingReparatie.getBon().getId(), existingReparatie.getBon());
        }

        // Opslaan van bijgewerkte reparatie
        return repos.save(existingReparatie);
    }


    private double berekenTotaalBedrag(Reparatie reparatie) {
        // Hier implementeer je de logica om het totaalbedrag van de reparatie te berekenen
        // Dit kan bijvoorbeeld het optellen zijn van de bedragen van gebruikte onderdelen en handelingen
        return 0.0; // Dit is slechts een placeholder, vervang dit door de werkelijke berekening
    }




//public Reparatie updateReparatie(Long id, Reparatie updatedReparatie) {
//    if (repos.existsById(id)) {
//        updatedReparatie.setId(id);
//        return repos.save(updatedReparatie);
//    }
//    return null; //
//}

// Methode om een reparatie te verwijderen op basis van ID
public void deleteReparatie(Long id) {
    repos.deleteById(id);
}


    public void setReparatieNietUitvoeren(Long reparatieId) {
        // Controleer of het reparatie-id null is of niet geldig
        if (reparatieId == null) {
            throw new IllegalArgumentException("ReparatieId cannot be null");
        }

        // Zoek de reparatie op basis van het gegeven id
        Reparatie reparatie = repos.findById(reparatieId).orElseThrow(() ->
                new ResourceNotFoundException("Reparatie not found with id " + reparatieId)
        );

        // Stel de status van de reparatie in op "niet uitvoeren"
        reparatie.setStatus("niet uitvoeren");

        // Controleer of de bon correct wordt aangemaakt
        BonDto newBon = new BonDto(); // Bon aanmaken
        newBon.setBedrag(0.0); // Of een ander standaardbedrag indien nodig
        Bon createdBon = bonService.createBon(newBon); // Opslaan van bon

        if (createdBon == null) {
            throw new RuntimeException("Failed to create a new bon");
        }

        // Koppel de bon aan de reparatie
        reparatie.setBon(createdBon);

        // Sla de gewijzigde reparatie op in de database
        repos.save(reparatie);
    }


//    public void setReparatieNietUitvoeren(Long reparatieId) {
//        Reparatie reparatie = repos.findById(reparatieId).orElse(null);
//        if (reparatie != null) {
//            reparatie.setStatus("niet uitvoeren");
////            Bon maken voor keuring
//            Bon bon = bonService.createBon(new Bon());
//            reparatie.setBon(bon);
//
//            repos.save(reparatie);
//        }
//    }

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

package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.BonDto;
import nl.novi.autogarage.exception.ResourceNotFoundException;
import nl.novi.autogarage.model.Bon;
import nl.novi.autogarage.repository.BonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BonService {
    @Autowired
    private BonRepository bonRepository;

    public List<Bon> getAllBons() {
        return bonRepository.findAll();
    }

    public Bon getBonById(Long id) {
        return bonRepository.findById(id).orElse(null);
    }

    public Bon createBon(BonDto bonDto) {

        Bon newBon = new Bon();
        newBon.setBedrag(bonDto.getBedrag());
        // Standaardwaarden instellen
        newBon.setTotaalBedragInclusiefBtw(newBon.getBedrag() * 1.21); // BTW toevoegen, indien relevant
        newBon.setKeuringBedrag(10.0); // Voorbeeldwaarde voor keuring
        newBon.setHandelingenBedrag(20.0); // Voorbeeldwaarde voor handelingen
        newBon.setOnderdelenBedrag(15.0); // Voorbeeldwaarde voor onderdelen
        return bonRepository.save(newBon);
    }

    private double berekenTotaalBedrag(Bon bon) {
        return bon.getKeuringBedrag() + bon.getHandelingenBedrag() + bon.getOnderdelenBedrag();
    }


    public Bon updateBon(Long id, Bon bonDetails) {
        Bon existingBon = bonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bon not found with id " + id));

        existingBon.setKeuringBedrag(bonDetails.getKeuringBedrag());
        existingBon.setHandelingenBedrag(bonDetails.getHandelingenBedrag());
        existingBon.setOnderdelenBedrag(bonDetails.getOnderdelenBedrag());

        return bonRepository.save(existingBon);
    }
//    public Bon updateBon(Long id, Bon bon) {
//        if (bonRepository.existsById(id)) {
//            bon.setId(id);
//            return bonRepository.save(bon);
//        }
//        return null;
//    }

    public void deleteBon(Long id) {
        bonRepository.deleteById(id);
    }


}

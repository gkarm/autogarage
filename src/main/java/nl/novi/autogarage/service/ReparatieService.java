package nl.novi.autogarage.service;

import nl.novi.autogarage.model.Reparatie;
import nl.novi.autogarage.repository.ReparatieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ReparatieService {
    private final ReparatieRepository repos;

    @Autowired

    public ReparatieService(ReparatieRepository repos) {
        this.repos = repos;
    }
//    Methode om alle reparaties op te halen

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


}

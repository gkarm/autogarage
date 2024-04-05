package nl.novi.autogarage.service;

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

    public Bon createBon(Bon bon) {
        return bonRepository.save(bon);
    }

    public Bon updateBon(Long id, Bon bon) {
        if (bonRepository.existsById(id)) {
            bon.setId(id);
            return bonRepository.save(bon);
        }
        return null;
    }

    public void deleteBon(Long id) {
        bonRepository.deleteById(id);
    }
}

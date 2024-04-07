package nl.novi.autogarage.service;


import nl.novi.autogarage.dto.AutoDto;
import nl.novi.autogarage.model.Auto;
import nl.novi.autogarage.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService {
    @Autowired
    private final AutoRepository repos;

    public AutoService(AutoRepository repos) {
        this.repos = repos;
    }

    public List<Auto> getAllAuto() {
        return repos.findAll();
    }
    public Auto getAutoById(Long id) {
        return repos.findById(id).orElse(null);
    }

    public AutoDto createAuto(AutoDto autoDto) {
        Auto auto = new Auto();
        auto.setBouwjaar(autoDto.bouwjaar);
        auto.setMerk(autoDto.merk);
        auto.setKenteken(autoDto.kenteken);
        auto.setModel(autoDto.model);
        repos.save(auto);
        autoDto.id = auto.getId();

        return autoDto;
    }
    public Auto updateAuto(Long id, Auto auto) {
        if (repos.existsById(id)) {
            auto.setId(id);
            return repos.save(auto);
        }
        return null;
    }

    public void deleteAuto(Long id) {
        repos.deleteById(id);
    }

}

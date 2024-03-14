package nl.novi.autogarage.service;


import nl.novi.autogarage.dto.AutoDto;
import nl.novi.autogarage.model.Auto;
import nl.novi.autogarage.repository.AutoRepository;
import org.springframework.stereotype.Service;

@Service
public class AutoService {
    private final AutoRepository repos;

    public AutoService(AutoRepository repos) {
        this.repos = repos;
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

}

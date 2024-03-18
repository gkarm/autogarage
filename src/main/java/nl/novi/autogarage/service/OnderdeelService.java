package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.OnderdeelDto;
import nl.novi.autogarage.model.Onderdeel;
import nl.novi.autogarage.repository.OnderdeelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnderdeelService {
    private final OnderdeelRepository repos;

    @Autowired

    public OnderdeelService(OnderdeelRepository repos) {
    this.repos = repos;
}
public List<Onderdeel> getAllOnderdelen() {
        return repos.findAll();
}
public Onderdeel getOnderdeelById(Long id) {
        return repos.findById(id).orElse(null);
}

    public Onderdeel addOnderdeel(Onderdeel onderdeel) {
        return repos.save(onderdeel);
    }

    public Onderdeel updateOnderdeel(Long id, Onderdeel updatedOnderdeel) {
        if (repos.existsById(id)) {
            updatedOnderdeel.setId(id);
            return repos.save(updatedOnderdeel);
        }
        return null;
    }
    public void deleteOnderdeel(Long id) {
        repos.deleteById(id);
    }


}

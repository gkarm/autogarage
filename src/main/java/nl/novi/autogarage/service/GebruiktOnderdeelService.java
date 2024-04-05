package nl.novi.autogarage.service;

import nl.novi.autogarage.model.GebruiktOnderdeel;
import nl.novi.autogarage.repository.GebruiktOnderdeelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GebruiktOnderdeelService {
    @Autowired
    private GebruiktOnderdeelRepository gebruiktOnderdeelRepository;

    public GebruiktOnderdeel createGebruiktOnderdeel(GebruiktOnderdeel gebruiktOnderdeel) {
        return gebruiktOnderdeelRepository.save(gebruiktOnderdeel);
    }

    public GebruiktOnderdeel getGebruiktOnderdeelById(Long id) {
        return gebruiktOnderdeelRepository.findById(id).orElse(null);
    }

    public void deleteGebruiktOnderdeel(Long id) {
        gebruiktOnderdeelRepository.deleteById(id);
    }
}

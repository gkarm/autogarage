package nl.novi.autogarage.service;

import nl.novi.autogarage.model.GebruiktOnderdeel;
import nl.novi.autogarage.model.Onderdeel;
import nl.novi.autogarage.model.Reparatie;
import nl.novi.autogarage.repository.GebruiktOnderdeelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GebruiktOnderdeelService {
    @Autowired
    private GebruiktOnderdeelRepository gebruiktOnderdeelRepository;
    private GebruiktOnderdeel gebruiktOnderdeel;

    public GebruiktOnderdeel createGebruiktOnderdeel(Reparatie reparatie, Onderdeel onderdeel) {
/*
        GebruiktOnderdeel gebruiktOnderdeel = new GebruiktOnderdeel();
        gebruiktOnderdeel.setReparatie(reparatie);
        gebruiktOnderdeel.setOnderdeel(onderdeel);
*/
        return gebruiktOnderdeelRepository.save(gebruiktOnderdeel);
    }

//    public GebruiktOnderdeel createGebruiktOnderdeel(GebruiktOnderdeel gebruiktOnderdeel) {
//        return gebruiktOnderdeelRepository.save(gebruiktOnderdeel);
//    }

    public GebruiktOnderdeel getGebruiktOnderdeelById(Long id) {
        return gebruiktOnderdeelRepository.findById(id).orElse(null);
    }

    public void deleteGebruiktOnderdeel(Long id) {
        gebruiktOnderdeelRepository.deleteById(id);
    }

    public GebruiktOnderdeel createGebruiktOnderdeel(GebruiktOnderdeel gebruiktOnderdeel) {
        return null;
    }
}

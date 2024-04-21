package nl.novi.autogarage.service;


import nl.novi.autogarage.dto.AutoDto;
import nl.novi.autogarage.model.*;
import nl.novi.autogarage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoService {
    @Autowired
    private final AutoRepository repos;
    private final MonteurRepository monteurRepos;
    private final AdMedewerkerRepository adMedewerkerRepository;
    private final KlantRepository klantRepository;

    private final KassaMedewerkerRepository kassaMedewerkerRepository;
    private final BoMedewerkerRepository boMedewerkerRepository;

    private KeuringRepository autoRepository;

    public AutoService(AutoRepository repos, MonteurRepository monteurRepos, AdMedewerkerRepository adMedewerkerRepository, KlantRepository klantRepository, KassaMedewerkerRepository kassaMedewerkerRepository, BoMedewerkerRepository boMedewerkerRepository) {
        this.repos = repos;
        this.monteurRepos = monteurRepos;
        this.adMedewerkerRepository = adMedewerkerRepository;
        this.klantRepository = klantRepository;

        this.kassaMedewerkerRepository = kassaMedewerkerRepository;
        this.boMedewerkerRepository = boMedewerkerRepository;
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

        for (Long id : autoDto.monteurIds) {
            Optional<Monteur> om = monteurRepos.findById(id);
            if (om.isPresent()) {
                Monteur monteur = om.get();
                auto.getMonteurs().add(monteur);
            }
        }

            for (Long id : autoDto.admedewerker) {
                Optional<AdMedewerker> oa = adMedewerkerRepository.findById(id);
                if (oa.isPresent()) {
                    AdMedewerker admedewerker = oa.get();
                    auto.setAdmedewerker(admedewerker);
                }
            }

            for (Long id : autoDto.klantIds) {
                Optional<Klant> ok = klantRepository.findById(id);
                if (ok.isPresent()) {
                    Klant klant = ok.get();
                    auto.setKlant(klant);
                    }
                }
        for (Long id : autoDto.bomedewerker) {
            Optional<BoMedewerker> ob = boMedewerkerRepository.findById(id);
            if (ob.isPresent()) {
                BoMedewerker boMedewerker = ob.get();
                auto.setBoMedewerker(boMedewerker);
            }
        }

        for (Long id : autoDto.kassamedewerker) {
            Optional<KassaMedewerker> oka = kassaMedewerkerRepository.findById(id);
            if (oka.isPresent()) {
                KassaMedewerker kassaMedewerker = oka.get();
                auto.setKassaMedewerker(kassaMedewerker);
            }
        }




        repos.save(auto);
        autoDto.id = auto.getId();

        return autoDto;
    }




    public Auto updateAuto(Long id, Auto auto) {  if (repos.existsById(id)) {
        auto.setId(id);
        return repos.save(auto);
    }
        return null;
    }

    public void deleteAuto(Long id) { repos.deleteById(id);
    }

    public void addTekortkomingToAuto(Long autoId, Tekortkoming tekortkoming) {
        Auto auto = repos.findById(autoId).orElse(null);
        if (auto != null) {
            tekortkoming.setAuto(auto);
            auto.getTekortkomingen().add(tekortkoming);
            repos.save(auto);
        }
    }
    }

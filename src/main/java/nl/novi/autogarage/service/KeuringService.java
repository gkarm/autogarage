package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.KeuringDto;
import nl.novi.autogarage.model.Keuring;
import nl.novi.autogarage.repository.KeuringRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KeuringService {

    private final KeuringRepository repos;

    public KeuringService(KeuringRepository repos) {
        this.repos = repos;
    }

    public List<KeuringDto> getAllKeuringen() {
        List<Keuring> keuringList = repos.findAll();
        List<KeuringDto> keuringDtoList = new ArrayList<>();
        for (Keuring keuring : keuringList) {
            keuringDtoList.add(keuringDtoFromKeuring(keuring));

        }

        return keuringDtoList;
    }
    public Keuring getKeuringById(Long id) {
        Optional<Keuring> keuringOptional = repos.findById(id);
        return keuringOptional.orElse(null);
    }

    public Keuring addKeuring(Keuring keuring) {
        return repos.save(keuring);
    }

    public Keuring updateKeuring(Long id, Keuring updatedKeuring) {
        Optional<Keuring> keuringOptional = repos.findById(id);
        if (keuringOptional.isPresent()) {
            updatedKeuring.setId(id);
            return repos.save(updatedKeuring);
        }
        return null;
    }
    public KeuringDto keuringDtoFromKeuring(Keuring keuring) {
        KeuringDto keuringDto = new KeuringDto();
        keuringDto.setKeuringsResultaat(keuring.getKeuringsResultaat());
        keuringDto.setMonteur(keuring.getMonteur());
        keuringDto.setStatus(keuring.getStatus());
        keuringDto.setDatum(keuring.getDatum());
        keuringDto.setAuto(keuring.getAuto());
        keuringDto.setId(keuring.getId());
        keuringDto.setOpmerking(keuring.getOpmerking());

        return keuringDto;

    }
}


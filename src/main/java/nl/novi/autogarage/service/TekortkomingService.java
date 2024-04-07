package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.TekortkomingDto;
import nl.novi.autogarage.model.Tekortkoming;
import nl.novi.autogarage.repository.TekortkomingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TekortkomingService {
    @Autowired
    private TekortkomingRepository tekortkomingRepository;

    public Tekortkoming createTekortkoming(Tekortkoming tekortkoming) {
        return tekortkomingRepository.save(tekortkoming);
    }

    public Tekortkoming getTekortkomingById(Long id) {
        return tekortkomingRepository.findById(id).orElse(null);
    }

    public Tekortkoming updateTekortkoming(Long id, TekortkomingDto tekortkomingDto) {
        Tekortkoming tekortkoming = tekortkomingRepository.findById(id).orElse(null);
        if (tekortkoming != null) {
            tekortkoming.setBeschrijving(tekortkomingDto.getBeschrijving());
            tekortkoming.setOplossing(tekortkomingDto.getOplossing());
            return tekortkomingRepository.save(tekortkoming);
        }
        return null;
    }

    public void deleteTekortkoming(Long id) {
        tekortkomingRepository.deleteById(id);
    }
}

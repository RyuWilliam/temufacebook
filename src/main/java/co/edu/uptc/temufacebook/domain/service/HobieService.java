package co.edu.uptc.temufacebook.domain.service;

import co.edu.uptc.temufacebook.domain.dto.HobieDTO;
import co.edu.uptc.temufacebook.domain.repository.HobieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HobieService {

    private final HobieRepository hobieRepository;

    public HobieService(HobieRepository hobieRepository) {
        this.hobieRepository = hobieRepository;
    }

    public HobieDTO createHobie(HobieDTO hobieDTO) {
        return hobieRepository.saveHobie(hobieDTO);
    }

    public HobieDTO getHobieById(int id) {
        return hobieRepository.getById(id);
    }

    public HobieDTO getHobieByName(String name) {
        return hobieRepository.getByName(name);
    }

    public List<HobieDTO> getAllHobbies() {
        return hobieRepository.getAll();
    }

    public void deleteHobie(int id) {
        hobieRepository.deleteHobie(id);
    }

    public HobieDTO updateHobie(HobieDTO hobieDTO) {
        return hobieRepository.updateHobie(hobieDTO);
    }

    public List<HobieDTO> getHobbiesByPerson(int personId) {
        return hobieRepository.getByPerson(personId);
    }
}

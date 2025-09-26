package co.edu.uptc.temufacebook.domain.service;

import co.edu.uptc.temufacebook.domain.dto.PlaceDTO;
import co.edu.uptc.temufacebook.domain.repository.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public PlaceDTO createPlace(PlaceDTO placeDTO) {
        return placeRepository.savePlace(placeDTO);
    }

    public PlaceDTO getPlaceById(int id) {
        return placeRepository.getById(id);
    }

    public PlaceDTO getPlaceByName(String name) {
        return placeRepository.getByName(name);
    }

    public List<PlaceDTO> getAllPlaces() {
        return placeRepository.getAll();
    }

    public void deletePlace(int id) {
        placeRepository.deletePlace(id);
    }

    public PlaceDTO updatePlace(PlaceDTO placeDTO) {
        return placeRepository.updatePlace(placeDTO);
    }

    public List<PlaceDTO> getPlaceEvents(int placeId) {
        return placeRepository.getEvents(placeId);
    }
}
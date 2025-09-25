package co.edu.uptc.temufacebook.domain.repository;

import co.edu.uptc.temufacebook.domain.dto.PlaceDTO;

import java.util.List;

public interface PlaceRepository {

    PlaceDTO savePlace(PlaceDTO PlaceDTO);

    PlaceDTO getById(int id);

    PlaceDTO getByName(String name);

    List<PlaceDTO> getAll();

    void deletePlace(int id);

    PlaceDTO updatePlace(PlaceDTO PlaceDTO);

    List<PlaceDTO> getEvents(int PlaceId);
}

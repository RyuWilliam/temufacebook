package co.edu.uptc.temufacebook.domain.repository;

import co.edu.uptc.temufacebook.domain.dto.HobieDTO;

import java.util.List;

public interface HobieRepository {

    HobieDTO saveHobie(HobieDTO hobie);

    HobieDTO getById(int id);

    HobieDTO getByName(String name);

    List<HobieDTO> getAll();

    void deleteHobie(int id);

    HobieDTO updateHobie(HobieDTO hobie);

    List<HobieDTO> getByPerson(int personId);
}

package co.edu.uptc.temufacebook.domain.repository;

import co.edu.uptc.temufacebook.domain.dto.Hobie;

public interface HobieRepository {
    Hobie saveHobie(Hobie hobie);
    Hobie getById(int id);
    Hobie getByName(String name);
    void deleteHobie(int id);
    Hobie updateHobie(Hobie hobie);
}

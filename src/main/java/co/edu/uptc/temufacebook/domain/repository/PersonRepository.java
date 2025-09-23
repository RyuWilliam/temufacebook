package co.edu.uptc.temufacebook.domain.repository;

import co.edu.uptc.temufacebook.domain.dto.PersonDTO;

import java.util.List;

public interface PersonRepository {

    PersonDTO savePerson(PersonDTO personDTO);

    PersonDTO getById(int id);

    PersonDTO getByName(String name);

    List<PersonDTO> getAll();

    void deletePerson(int id);

    PersonDTO updatePerson(PersonDTO personDTO);

    List<PersonDTO> getFriends(int personId);
}

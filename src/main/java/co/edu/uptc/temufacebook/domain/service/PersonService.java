package co.edu.uptc.temufacebook.domain.service;

import co.edu.uptc.temufacebook.domain.dto.PersonDTO;
import co.edu.uptc.temufacebook.domain.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDTO createPerson(PersonDTO personDTO) {
        return personRepository.savePerson(personDTO);
    }

    public PersonDTO getPersonById(int id) {
        return personRepository.getById(id);
    }

    public PersonDTO getPersonByName(String name) {
        return personRepository.getByName(name);
    }

    public List<PersonDTO> getAllPersons() {
        return personRepository.getAll();
    }

    public void deletePerson(int id) {
        personRepository.deletePerson(id);
    }

    public List<PersonDTO> getFriends(int personId) {
        return personRepository.getFriends(personId);
    }
}

package co.edu.uptc.temufacebook.domain.repository;

import co.edu.uptc.temufacebook.domain.dto.Person;

public interface PersonRepository {

    Person savePerson(Person person);
    Person getById(int id);
    Person getByName(String name);
    void deletePerson(int id);
    Person updatePerson(Person person);


}

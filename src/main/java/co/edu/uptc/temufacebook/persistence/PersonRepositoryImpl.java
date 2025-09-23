package co.edu.uptc.temufacebook.persistence;

import co.edu.uptc.temufacebook.domain.dto.PersonDTO;
import co.edu.uptc.temufacebook.domain.repository.PersonRepository;
import co.edu.uptc.temufacebook.persistence.crudRepository.neo4jRepositories.PersonNeo4jRepository;
import co.edu.uptc.temufacebook.persistence.entity.neo4jEntities.PersonNode;
import co.edu.uptc.temufacebook.persistence.mapper.PersonMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonNeo4jRepository personNeo4jRepository;
    private final PersonMapper personMapper;

    public PersonRepositoryImpl(PersonNeo4jRepository personNeo4jRepository, PersonMapper personMapper) {
        this.personNeo4jRepository = personNeo4jRepository;
        this.personMapper = personMapper;
    }

    @Override
    public PersonDTO savePerson(PersonDTO personDTO) {
        PersonNode node = personMapper.toNode(personDTO);
        PersonNode saved = personNeo4jRepository.save(node);
        return personMapper.toDTO(saved);
    }

    @Override
    public PersonDTO getById(int id) {
        PersonNode node = personNeo4jRepository.findById((long)id).orElse(null);
        return personMapper.toDTO(node);
    }

    @Override
    public PersonDTO getByName(String name) {
        return personMapper.toDTO(personNeo4jRepository.findByName(name));
    }

    @Override
    public List<PersonDTO> getAll() {
        return personMapper.toDTOs(personNeo4jRepository.findAll());
    }

    @Override
    public void deletePerson(int id) {
        personNeo4jRepository.deleteById((long)id);
    }


    //Implementar
    @Override
    public PersonDTO updatePerson(PersonDTO personDTO) {
        return null;
    }

    @Override
    public List<PersonDTO> getFriends(int personId) {
        List<PersonNode> friends = personNeo4jRepository.findFriendsById((long) personId);
        if (friends == null || friends.isEmpty()) {
            return null;
        }
        return personMapper.toDTOs(friends);
    }
}

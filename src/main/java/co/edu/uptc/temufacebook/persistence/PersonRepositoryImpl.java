package co.edu.uptc.temufacebook.persistence;

import co.edu.uptc.temufacebook.domain.dto.PersonDTO;
import co.edu.uptc.temufacebook.domain.repository.PersonRepository;
import co.edu.uptc.temufacebook.persistence.crudRepository.mongoRepositories.PersonMongoRepository;
import co.edu.uptc.temufacebook.persistence.crudRepository.neo4jRepositories.PersonNeo4jRepository;
import co.edu.uptc.temufacebook.persistence.crudRepository.postgresRepositories.PersonPostgresRepository;
import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.PersonDocument;
import co.edu.uptc.temufacebook.persistence.entity.neo4jEntities.PersonNode;
import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.PersonEntity;
import co.edu.uptc.temufacebook.persistence.mapper.PersonMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonNeo4jRepository personNeo4jRepository;
    private final PersonPostgresRepository personPostgresRepository;
    private final PersonMongoRepository personMongoRepository;
    private final PersonMapper personMapper;

    public PersonRepositoryImpl(PersonNeo4jRepository personNeo4jRepository,
            PersonPostgresRepository personPostgresRepository, PersonMongoRepository personMongoRepository,
            PersonMapper personMapper) {
        this.personNeo4jRepository = personNeo4jRepository;
        this.personPostgresRepository = personPostgresRepository;
        this.personMongoRepository = personMongoRepository;
        this.personMapper = personMapper;
    }

    @Override
    public PersonDTO savePerson(PersonDTO personDTO) {
        PersonEntity entity = personMapper.toEntity(personDTO);
        PersonEntity savedEntity = personPostgresRepository.save(entity);


        PersonDocument document = personMapper.toDocument(personDTO);
        document.setPersonId(savedEntity.getPersonId());
        PersonDocument savedDocument = personMongoRepository.save(document);

        PersonNode node = personMapper.toNode(personDTO);
        node.setPersonId(savedDocument.getPersonId());
        personNeo4jRepository.save(node);
        return personMapper.fromDocument(savedDocument);
    }

    @Override
    public PersonDTO getById(int id) {
        PersonEntity entity = personPostgresRepository.findByPersonId((long) id);
        if (entity == null) {
            return null;
        }
        return personMapper.fromEntity(entity);
    }

    @Override
    public PersonDTO getByName(String name) {
        PersonEntity entity = personPostgresRepository.findByName(name);
        if (entity != null) {
            return personMapper.fromEntity(entity);
        }
        return null;
    }

    @Override
    public List<PersonDTO> getAll() {
        List<PersonDocument> documents = personMongoRepository.findAll();
        if (documents != null && !documents.isEmpty()) {
            return personMapper.fromDocuments(documents);
        }
        return null;
    }

    @Override
    public void deletePerson(int id) {
        Long personId = (long) id;

        if (!personMongoRepository.existsById(personId)) {
            throw new RuntimeException("Person with ID " + id + " does not exist.");
        }

        try {
            PersonNode personNode = personNeo4jRepository.findByPersonId(personId);
            if (personNode != null) {
                personNeo4jRepository.delete(personNode);
            }
            personPostgresRepository.deleteByPersonId(personId);
            personMongoRepository.deleteById(personId);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting person with ID " + id + ": " + e.getMessage());
        }
    }

    @Override
    public PersonDTO updatePerson(PersonDTO personDTO) {
        if (personDTO != null && personDTO.getPersonId() != null) {
            Long personId = personDTO.getPersonId();
            if (personMongoRepository.existsById(personId)) {
                PersonDocument document = personMapper.toDocument(personDTO);
                PersonDocument updatedDocument = personMongoRepository.save(document);

                PersonNode node = personMapper.toNode(personDTO);
                node.setPersonId(updatedDocument.getPersonId());
                personNeo4jRepository.save(node);

                PersonEntity entity = personMapper.toEntity(personDTO);
                entity.setPersonId(updatedDocument.getPersonId());
                personPostgresRepository.save(entity);
                return personMapper.fromDocument(updatedDocument);
            } else {
                throw new RuntimeException("Person with ID " + personId + " does not exist.");
            }

        }
        return null;
    }

    @Override
    public List<PersonDTO> getFriends(int personId) {
        List<PersonNode> friends = personNeo4jRepository.findFriendsByPersonId((long) personId);
        if (friends == null || friends.isEmpty()) {
            return List.of();
        }

        List<Long> friendIds = friends.stream().map(PersonNode::getPersonId).toList();
        List<PersonDocument> documents = personMongoRepository.findAllById(friendIds);
        if (documents == null || documents.isEmpty()) {
            return List.of();
        }
        return personMapper.fromDocuments(documents);
    }

    @Override
    public void addFriend(int personId, int friendId) {
        try {
        if (!personMongoRepository.existsById((long) personId)) {
            throw new RuntimeException("Person with ID " + personId + " does not exist.");
        }

        if (!personMongoRepository.existsById((long) friendId)) {
            throw new RuntimeException("Person with ID " + friendId + " does not exist.");
        }
        
        if (personId == friendId) {
            throw new RuntimeException("Una persona no puede ser amiga de sí misma.");
        }
        
        if (areFriends(personId, friendId)) {
            throw new RuntimeException("Persons with IDs " + personId + " and " + friendId + " are already friends.");
        }
        
        PersonNode person = personNeo4jRepository.findByPersonId((long) personId);
        PersonNode friend = personNeo4jRepository.findByPersonId((long) friendId);
        
        if (person != null && friend != null) {
            personNeo4jRepository.createFriendRelation(person.getPersonId(), friend.getPersonId());
            System.out.println("Friendship created between " + personId + " and " + friendId);
        } else {
            throw new RuntimeException("Could not find persons in Neo4j database.");
        }
        
    } catch (Exception e) {
        System.err.println("Error adding friend: " + e.getMessage());
        throw new RuntimeException("Error creating friendship: " + e.getMessage());
    }
    }

    private boolean areFriends(int personId, int friendId) {
    try {
        List<PersonNode> friends = personNeo4jRepository.findFriendsByPersonId((long) personId);
        return friends.stream()
                .anyMatch(friend -> friend.getPersonId().equals((long) friendId));
    } catch (Exception e) {
        System.err.println("Error checking friendship: " + e.getMessage());
        return false;
    }
}
}

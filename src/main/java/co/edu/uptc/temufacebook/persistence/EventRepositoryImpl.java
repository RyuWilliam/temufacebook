package co.edu.uptc.temufacebook.persistence;

import co.edu.uptc.temufacebook.domain.dto.EventDTO;
import co.edu.uptc.temufacebook.domain.repository.EventRepository;
import co.edu.uptc.temufacebook.persistence.crudRepository.mongoRepositories.EventMongoRepository;
import co.edu.uptc.temufacebook.persistence.crudRepository.neo4jRepositories.EventNeo4jRepository;
import co.edu.uptc.temufacebook.persistence.crudRepository.postgresRepositories.EventPostgresRepository;
import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.EventDocument;
import co.edu.uptc.temufacebook.persistence.entity.neo4jEntities.EventNode;
import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.EventEntity;
import co.edu.uptc.temufacebook.persistence.mapper.EventMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private final EventNeo4jRepository eventNeo4jRepository;
    private final EventPostgresRepository eventPostgresRepository;
    private final EventMongoRepository eventMongoRepository;
    private final EventMapper eventMapper;

    public EventRepositoryImpl(EventNeo4jRepository eventNeo4jRepository,
            EventPostgresRepository eventPostgresRepository, EventMongoRepository eventMongoRepository,
            EventMapper eventMapper) {
        this.eventNeo4jRepository = eventNeo4jRepository;
        this.eventPostgresRepository = eventPostgresRepository;
        this.eventMongoRepository = eventMongoRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public EventDTO saveEvent(EventDTO eventDTO) {
        EventEntity entity = eventMapper.toEntity(eventDTO);
        EventEntity savedEntity = eventPostgresRepository.save(entity);

        EventDocument document = eventMapper.toDocument(eventDTO);
        document.setEventId(savedEntity.getEventId());
        EventDocument savedDocument = eventMongoRepository.save(document);


        EventNode node = eventMapper.toNode(eventDTO);
        node.setEventId(savedDocument.getEventId());
        eventNeo4jRepository.save(node);

        return eventMapper.fromDocument(savedDocument);
    }

    @Override
    public EventDTO getById(int id) {
        EventEntity entity = eventPostgresRepository.findByEventId((long) id);
        return eventMapper.fromEntity(entity);
    }

    @Override
    public List<EventDTO> getAll() {
        return eventMapper.fromDocuments(eventMongoRepository.findAll());
    }

    @Override
    public void changeStatus(int id, Status status) {
        EventEntity entity = eventPostgresRepository.findByEventId((long) id);
        EventDocument document = eventMongoRepository.findById((long) id).orElse(null);
        if (entity != null && document != null) {
            entity.setStatus(status);
            document.setStatus(status);
            eventMongoRepository.save(document);
            eventPostgresRepository.save(entity);
        }
    }

    @Override
    public void deleteEvent(int id) {
        Long eventId = (long) id;

        if (!eventMongoRepository.existsById(eventId)) {
            throw new RuntimeException("Event with ID " + id + " does not exist.");
        }

        try{
            EventNode eventNode = eventNeo4jRepository.findByEventId(eventId);
            if (eventNode != null) {
                eventNeo4jRepository.delete(eventNode);
            }
            eventNeo4jRepository.deleteById(eventId);
            eventPostgresRepository.deleteByEventId(eventId);
            eventMongoRepository.deleteById(eventId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete Event from Neo4j: " + e.getMessage());
        }
    }

    @Override
    public EventDTO updateEvent(EventDTO eventDTO) {
        if (eventDTO.getEventId() == null)
            return null;
        EventNode existing = eventNeo4jRepository.findById(eventDTO.getEventId()).orElse(null);
        if (existing == null)
            return null;

        EventDocument document = eventMapper.toDocument(eventDTO);
        document.setEventId(existing.getEventId());
        EventDocument savedDocument = eventMongoRepository.save(document);

        EventEntity entity = eventMapper.toEntity(eventDTO);
        entity.setEventId(savedDocument.getEventId());
        eventPostgresRepository.save(entity);

        EventNode node = eventMapper.toNode(eventDTO);
        node.setEventId(savedDocument.getEventId());
        eventNeo4jRepository.save(node);

        return eventMapper.fromDocument(savedDocument);
    }

    @Override
    public List<EventDTO> getByStatus(Status status) {
        if (status == null) {
            return List.of();

        }
        List<EventDocument> documents = eventMongoRepository.findByStatus(status.toString());
        if (documents == null || documents.isEmpty()) {
            return List.of();

        }
        return eventMapper.fromDocuments(documents);
    }

    public List<EventDTO> getAssociatedEvents(int eventId) {
        List<EventNode> associated = eventNeo4jRepository.findAssociatedEventsByEventId((long) eventId);
        if (associated == null || associated.isEmpty())
            return List.of();

        List<Long> associatedIds = associated.stream().map(EventNode::getEventId).toList();

        List<EventDocument> docs = eventMongoRepository.findAllById(associatedIds);
        if (docs == null || docs.isEmpty()) {
            return List.of();
            
        }
        return eventMapper.fromDocuments(docs);
    }
}

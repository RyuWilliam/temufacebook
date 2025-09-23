package co.edu.uptc.temufacebook.persistence;

import co.edu.uptc.temufacebook.domain.dto.EventDTO;
import co.edu.uptc.temufacebook.domain.repository.EventRepository;
import co.edu.uptc.temufacebook.persistence.crudRepository.neo4jRepositories.EventNeo4jRepository;
import co.edu.uptc.temufacebook.persistence.entity.neo4jEntities.EventNode;
import co.edu.uptc.temufacebook.persistence.mapper.EventMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private final EventNeo4jRepository eventNeo4jRepository;
    private final EventMapper eventMapper;

    public EventRepositoryImpl(EventNeo4jRepository eventNeo4jRepository, EventMapper eventMapper) {
        this.eventNeo4jRepository = eventNeo4jRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public EventDTO saveEvent(EventDTO eventDTO) {
        EventNode node = eventMapper.toNode(eventDTO);
        EventNode saved = eventNeo4jRepository.save(node);
        return eventMapper.toDTO(saved);
    }

    @Override
    public EventDTO getById(int id) {
        EventNode node = eventNeo4jRepository.findById((long) id).orElse(null);
        return eventMapper.toDTO(node);
    }

    @Override
    public List<EventDTO> getAll() {
        return eventMapper.toDTOs(eventNeo4jRepository.findAll());
    }

    @Override
    public void changeStatus(int id, Status status) {

    }

    @Override
    public void deleteEvent(int id) {
        eventNeo4jRepository.deleteById((long) id);
    }

    @Override
    public EventDTO updateEvent(EventDTO eventDTO) {
        if (eventDTO.getEventId() == null) return null;
        EventNode existing = eventNeo4jRepository.findById(eventDTO.getEventId()).orElse(null);
        if (existing == null) return null;

        EventNode updatedNode = eventMapper.toNode(eventDTO);
        updatedNode.setId(existing.getId());
        EventNode saved = eventNeo4jRepository.save(updatedNode);
        return eventMapper.toDTO(saved);
    }

    @Override
    public List<EventDTO> getByStatus(Status status) {
        return List.of();
    }

    public List<EventDTO> getAssociatedEvents(int eventId) {
        List<EventNode> associated = eventNeo4jRepository.findAssociatedEventsById((long) eventId);
        if (associated == null || associated.isEmpty()) return null;
        return eventMapper.toDTOs(associated);
    }

    public List<EventDTO> getParticipants(int eventId) {
        EventNode node = eventNeo4jRepository.findById((long) eventId).orElse(null);
        if (node == null || node.getParticipants() == null || node.getParticipants().isEmpty()) return null;
        return eventMapper.toDTOs(node.getAssociatedEvents()); // o mapear a participantes según corresponda
    }
}

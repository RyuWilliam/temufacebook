package co.edu.uptc.temufacebook.persistence;

import co.edu.uptc.temufacebook.domain.dto.Event;
import co.edu.uptc.temufacebook.domain.dto.Status;
import co.edu.uptc.temufacebook.domain.repository.EventRepository;
import co.edu.uptc.temufacebook.persistence.crudRepository.mongoRepositories.EventMongoRepository;
import co.edu.uptc.temufacebook.persistence.crudRepository.neo4jRepositories.EventGraphRepository;
import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.EventDocument;
import co.edu.uptc.temufacebook.persistence.entity.neo4jEntities.EventNode;
import co.edu.uptc.temufacebook.persistence.mapper.EventMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private final EventMongoRepository mongoRepository;
    private final EventGraphRepository neo4jRepository;
    private final EventMapper mapper;

    public EventRepositoryImpl(EventMongoRepository mongoRepository,
                               EventGraphRepository neo4jRepository,
                               EventMapper mapper) {
        this.mongoRepository = mongoRepository;
        this.neo4jRepository = neo4jRepository;
        this.mapper = mapper;
    }

    @Override
    public Event saveEvent(Event event) {
        // Guardar datos en Mongo
        EventDocument savedDoc = mongoRepository.save(mapper.toDocument(event));
        // Guardar relaciones en Neo4j
        EventNode savedNode = neo4jRepository.save(mapper.toNode(event));
        // Retornar DTO (podemos tomarlo de Mongo como fuente principal)
        return mapper.toDTO(savedNode);
    }

    @Override
    public Event getById(int id) {
        // Primero buscamos en Mongo (datos básicos)
        Optional<EventDocument> eventDoc = mongoRepository.findById(String.valueOf(id));
        if (eventDoc.isPresent()) {
            return mapper.toDTO(eventDoc.get());
        }
        // Si no está en Mongo, intentamos en Neo4j
        Optional<EventNode> eventNode = neo4jRepository.findById((long)id);
        return eventNode.map(mapper::toDTO).orElse(null);
    }
    @Override
    public void changeStatus(int id, Status status) {
        Optional<EventDocument> eventDocOpt = mongoRepository.findById(String.valueOf(id));
        eventDocOpt.ifPresent(doc -> {
            doc.setStatus(status.name()); // 🔑 guardamos como String
            mongoRepository.save(doc);
        });

        Optional<EventNode> eventNodeOpt = neo4jRepository.findById((long)id);
        eventNodeOpt.ifPresent(node -> {
            node.setStatus(status.name()); // idem en Neo4j si lo modelaste como String
            neo4jRepository.save(node);
        });
    }



    @Override
    public void deleteEvent(int id) {
        mongoRepository.deleteById(String.valueOf(id));
        neo4jRepository.deleteById((long)id);
    }

    @Override
    public Event updateEvent(Event event) {
        // Básicamente un save que sobrescribe
        EventDocument updatedDoc = mongoRepository.save(mapper.toDocument(event));
        EventNode updatedNode = neo4jRepository.save(mapper.toNode(event));
        return mapper.toDTO(updatedNode);
    }
}

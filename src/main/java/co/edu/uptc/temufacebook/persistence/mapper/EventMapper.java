package co.edu.uptc.temufacebook.persistence.mapper;

import co.edu.uptc.temufacebook.domain.dto.Event;
import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.EventDocument;
import co.edu.uptc.temufacebook.persistence.entity.neo4jEntities.EventNode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    // ---- De dominio a Mongo ----
    @Mapping(target = "id", source = "eventId", qualifiedByName = "intToString")
    @Mapping(target = "status", source = "status", qualifiedByName = "statusToString")
    @Mapping(target = "hobieIds", expression = "java(event.getHobieList() != null ? " +
            "event.getHobieList().stream().map(h -> String.valueOf(h.getHobieId())).toList() : null)")
    @Mapping(target = "participantIds", expression = "java(event.getParticipants() != null ? " +
            "event.getParticipants().stream().map(p -> String.valueOf(p.getPersonId())).toList() : null)")
    @Mapping(target = "associatedEventIds", expression = "java(event.getAssociatedEvents() != null ? " +
            "event.getAssociatedEvents().stream().map(e -> String.valueOf(e.getEventId())).toList() : null)")
    EventDocument toDocument(Event event);

    // ---- De dominio a Neo4j ----
    EventNode toNode(Event event);

    // ---- De Mongo a dominio ----
    @Mapping(target = "eventId", expression = "java(document.getId() != null ? Integer.parseInt(document.getId()) : null)")
    @Mapping(target = "status", expression = "java(document.getStatus() != null ? co.edu.uptc.temufacebook.domain.dto.Status.valueOf(document.getStatus()) : null)")
    Event toDTO(EventDocument document);

    // ---- De Neo4j a dominio ----
    Event toDTO(EventNode node);
}

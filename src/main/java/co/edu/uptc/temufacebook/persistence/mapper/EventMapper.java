package co.edu.uptc.temufacebook.persistence.mapper;

import co.edu.uptc.temufacebook.domain.dto.EventDTO;
import co.edu.uptc.temufacebook.persistence.entity.neo4jEntities.EventNode;
import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.EventDocument;
import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "associatedEvents", ignore = true) 
    EventNode toNode(EventDTO eventDTO);

    List<EventNode> toNodes(List<EventDTO> events);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "dateEvent", ignore = true)       
    @Mapping(target = "place", ignore = true)           
    @Mapping(target = "status", ignore = true)          
    @Mapping(target = "participants", ignore = true)    
    @Mapping(target = "events", ignore = true) 
    @Mapping(target = "hobbies", ignore = true)         
    EventDTO fromNode(EventNode eventNode);

    List<EventDTO> fromNodes(List<EventNode> events);

    // MongoDB Mappings
    @Mapping(target = "participants", ignore = true)
    @Mapping(target = "associatedEvents", ignore = true)
    @Mapping(target = "hobbies", ignore = true)
    EventDocument toDocument(EventDTO eventDTO);

    List<EventDocument> toDocuments(List<EventDTO> events);

    @Mapping(target = "participants", ignore = true)     
    @Mapping(target = "events", ignore = true) 
    @Mapping(target = "hobbies", ignore = true)          
    EventDTO fromDocument(EventDocument eventDocument);

    List<EventDTO> fromDocuments(List<EventDocument> events);

    // PostgreSQL Mappings
    @Mapping(target = "participants", ignore = true)     
    @Mapping(target = "hobbies", ignore = true)          
    EventEntity toEntity(EventDTO eventDTO);

    List<EventEntity> toEntities(List<EventDTO> events);

    @Mapping(target = "participants", ignore = true)     
    @Mapping(target = "events", ignore = true) 
    @Mapping(target = "hobbies", ignore = true)          
    EventDTO fromEntity(EventEntity eventEntity);

    List<EventDTO> fromEntities(List<EventEntity> events);

}


package co.edu.uptc.temufacebook.persistence.mapper;

import co.edu.uptc.temufacebook.domain.dto.EventDTO;
import co.edu.uptc.temufacebook.persistence.entity.neo4jEntities.EventNode;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface EventMapper {

    @Mapping(target = "id", source = "eventId")
    @Mapping(target = "participants", source = "participants")
    @Mapping(target = "associatedEvents", source = "associatedEvents")
    EventNode toNode(EventDTO eventDTO);

    List<EventNode> toNodes(List<EventDTO> events);

    @InheritInverseConfiguration
    EventDTO toDTO(EventNode eventNode);

    List<EventDTO> toDTOs(List<EventNode> events);

}


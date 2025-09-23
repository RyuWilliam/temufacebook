package co.edu.uptc.temufacebook.persistence.mapper;

import co.edu.uptc.temufacebook.domain.dto.PersonDTO;
import co.edu.uptc.temufacebook.persistence.entity.neo4jEntities.PersonNode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "id", source = "personId")
    @Mapping(target = "events", ignore = true)
    PersonNode toNode(PersonDTO dto);
    List<PersonNode> toNodes(List<PersonDTO> persons);

    @Mapping(target = "personId", source = "id")
    @Mapping(target = "events", ignore = true)
    PersonDTO toDTO(PersonNode node);
    List<PersonDTO> toDTOs(List<PersonNode> nodeList);


}

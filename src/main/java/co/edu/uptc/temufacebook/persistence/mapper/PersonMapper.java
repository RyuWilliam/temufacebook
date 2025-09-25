package co.edu.uptc.temufacebook.persistence.mapper;

import co.edu.uptc.temufacebook.domain.dto.PersonDTO;
import co.edu.uptc.temufacebook.persistence.entity.neo4jEntities.PersonNode;
import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.PersonDocument;
import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    // Neo4j Mappings (Solo IDs para relaciones)
    @Mapping(target = "friends", ignore = true) 
    PersonNode toNode(PersonDTO personDTO);
    
    List<PersonNode> toNodes(List<PersonDTO> persons);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "events", ignore = true)  
    @Mapping(target = "hobbies", ignore = true)
    PersonDTO fromNode(PersonNode personNode);
    
    List<PersonDTO> fromNodes(List<PersonNode> nodes);

    // MongoDB Mappings
    @Mapping(target = "friends", ignore = true) 
    @Mapping(target = "events", ignore = true)  
    @Mapping(target = "hobbies", ignore = true) 
    PersonDocument toDocument(PersonDTO personDTO);
    
    List<PersonDocument> toDocuments(List<PersonDTO> persons);

    @Mapping(target = "events", ignore = true)  
    @Mapping(target = "hobbies", ignore = true) 
    @Mapping(target = "friends", ignore = true) 
    PersonDTO fromDocument(PersonDocument personDocument);
    
    List<PersonDTO> fromDocuments(List<PersonDocument> documents);

    // PostgreSQL Mappings
    @Mapping(target = "events", ignore = true)  
    @Mapping(target = "hobbies", ignore = true) 
    PersonEntity toEntity(PersonDTO personDTO);
    
    List<PersonEntity> toEntities(List<PersonDTO> persons);

    @Mapping(target = "events", ignore = true)  
    @Mapping(target = "hobbies", ignore = true)
    @Mapping(target = "friends", ignore = true) 
    PersonDTO fromEntity(PersonEntity personEntity);
    
    List<PersonDTO> fromEntities(List<PersonEntity> entities);

}

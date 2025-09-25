package co.edu.uptc.temufacebook.persistence.mapper;

import co.edu.uptc.temufacebook.domain.dto.HobieDTO;
import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.HobieDocument;
import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.HobieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HobieMapper {

    // MongoDB Mappings
    @Mapping(target = "relatedEvents", ignore = true)   
    HobieDocument toDocument(HobieDTO hobieDTO);

    List<HobieDocument> toDocuments(List<HobieDTO> hobbies);

    @Mapping(target = "relatedEvents", ignore = true)   
    HobieDTO fromDocument(HobieDocument hobieDocument);

    List<HobieDTO> fromDocuments(List<HobieDocument> hobbies);

    // PostgreSQL Mappings
    @Mapping(target = "relatedEvents", ignore = true)   
    HobieEntity toEntity(HobieDTO hobieDTO);

    List<HobieEntity> toEntities(List<HobieDTO> hobbies);

    @Mapping(target = "relatedEvents", ignore = true)   
    HobieDTO fromEntity(HobieEntity hobieEntity);

    List<HobieDTO> fromEntities(List<HobieEntity> hobbies);

}
package co.edu.uptc.temufacebook.persistence.mapper;

import co.edu.uptc.temufacebook.domain.dto.PlaceDTO;
import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.PlaceDocument;
import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.PlaceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaceMapper {

    // MongoDB Mappings
    PlaceDocument toDocument(PlaceDTO placeDTO);
    
    List<PlaceDocument> toDocuments(List<PlaceDTO> places);

    PlaceDTO fromDocument(PlaceDocument placeDocument);
    
    List<PlaceDTO> fromDocuments(List<PlaceDocument> places);

    // PostgreSQL Mappings
    PlaceEntity toEntity(PlaceDTO placeDTO);
    
    List<PlaceEntity> toEntities(List<PlaceDTO> places);

    PlaceDTO fromEntity(PlaceEntity placeEntity);
    
    List<PlaceDTO> fromEntities(List<PlaceEntity> places);

}
package co.edu.uptc.temufacebook.persistence;

import co.edu.uptc.temufacebook.domain.dto.PlaceDTO;
import co.edu.uptc.temufacebook.domain.repository.PlaceRepository;
import co.edu.uptc.temufacebook.persistence.crudRepository.mongoRepositories.PlaceMongoRepository;
import co.edu.uptc.temufacebook.persistence.crudRepository.postgresRepositories.PlacePostgresRepository;
import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.PlaceDocument;
import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.PlaceEntity;
import co.edu.uptc.temufacebook.persistence.mapper.PlaceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceRepositoryImpl implements PlaceRepository {

    private final PlaceMongoRepository placeMongoRepository;
    private final PlacePostgresRepository placePostgresRepository;
    private final PlaceMapper placeMapper;

    public PlaceRepositoryImpl(PlaceMongoRepository placeMongoRepository,
                               PlacePostgresRepository placePostgresRepository, PlaceMapper placeMapper) {
        this.placeMongoRepository = placeMongoRepository;
        this.placePostgresRepository = placePostgresRepository;
        this.placeMapper = placeMapper;
    }

    @Override
    public PlaceDTO savePlace(PlaceDTO PlaceDTO) {
        PlaceEntity entity = placeMapper.toEntity(PlaceDTO);
        PlaceEntity savedEntity = placePostgresRepository.save(entity); 

        PlaceDocument document = placeMapper.toDocument(PlaceDTO);
        document.setPlaceId(savedEntity.getPlaceId());
        PlaceDocument savedDocument = placeMongoRepository.save(document);

        return placeMapper.fromDocument(savedDocument);
    }

    @Override
    public PlaceDTO getById(int id) {
        PlaceEntity entity = placePostgresRepository.findByPlaceId((long) id);
        if (entity == null) {
            return null;
        }
        return placeMapper.fromEntity(entity);
    }

    @Override
    public PlaceDTO getByName(String name) {
        PlaceDocument document = placeMongoRepository.findByName(name);
        if (document != null) {
            return placeMapper.fromDocument(document);
        }
        return null;
    }

    @Override
    public List<PlaceDTO> getAll() {
        List<PlaceDocument> documents = placeMongoRepository.findAll();
        if (!documents.isEmpty()) {
            return placeMapper.fromDocuments(documents);
        }
        return List.of();
    }

    @Override
    public void deletePlace(int id) {
        PlaceEntity entity = placePostgresRepository.findByPlaceId((long) id);
        if (entity != null) {
            placeMongoRepository.deleteById(entity.getPlaceId());
            placePostgresRepository.deleteById(entity.getPlaceId());
        }
    }

    @Override
    public PlaceDTO updatePlace(PlaceDTO PlaceDTO) {
        Long placeId = PlaceDTO.getPlaceId();
        if (placeMongoRepository.existsById(placeId)) {
            PlaceDocument document = placeMapper.toDocument(PlaceDTO);
            PlaceDocument updatedDocument = placeMongoRepository.save(document);

            PlaceEntity entity = placeMapper.toEntity(PlaceDTO);
            entity.setPlaceId(updatedDocument.getPlaceId());
            placePostgresRepository.save(entity);
            return placeMapper.fromDocument(updatedDocument);
        }else {
            throw new RuntimeException("Place with ID " + placeId + " does not exist.");
        }
    }

    @Override
    public List<PlaceDTO> getEvents(int PlaceId) {
        return List.of();
    }
}

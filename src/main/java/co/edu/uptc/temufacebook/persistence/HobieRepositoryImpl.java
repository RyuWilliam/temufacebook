package co.edu.uptc.temufacebook.persistence;

import co.edu.uptc.temufacebook.domain.dto.HobieDTO;
import co.edu.uptc.temufacebook.domain.repository.HobieRepository;
import co.edu.uptc.temufacebook.persistence.crudRepository.mongoRepositories.HobieMongoRepository;
import co.edu.uptc.temufacebook.persistence.crudRepository.postgresRepositories.HobiePostgresRepository;
import co.edu.uptc.temufacebook.persistence.entity.mongoEntities.HobieDocument;
import co.edu.uptc.temufacebook.persistence.entity.postgresEntities.HobieEntity;
import co.edu.uptc.temufacebook.persistence.mapper.HobieMapper;

import java.util.List;

public class HobieRepositoryImpl implements HobieRepository {

    private final HobieMongoRepository hobieMongoRepository;
    private final HobieMapper hobieMapper;
    private final HobiePostgresRepository hobiePostgresRepository;

    public HobieRepositoryImpl(HobieMongoRepository hobieMongoRepository,
                               HobiePostgresRepository hobiePostgresRepository, HobieMapper hobieMapper) {
        this.hobieMongoRepository = hobieMongoRepository;
        this.hobiePostgresRepository = hobiePostgresRepository;
        this.hobieMapper = hobieMapper;
    }

    @Override
    public HobieDTO saveHobie(HobieDTO hobieDTO) {
        try {
            HobieEntity entity = hobieMapper.toEntity(hobieDTO);
            HobieDocument document = hobieMapper.toDocument(hobieDTO);
            
            HobieEntity savedEntity = hobiePostgresRepository.save(entity);
            
            if (hobieDTO.getHobieId() == null) {
                document.setHobieId(savedEntity.getHobieId());
            }
            
            HobieDocument savedDocument = hobieMongoRepository.save(document);
            
            return hobieMapper.fromDocument(savedDocument);
            
        } catch (Exception e) {
            throw new RuntimeException("Error saving hobie: " + e.getMessage());
        }
    }

    @Override
    public HobieDTO getById(int id) {
        Long hobieId = (long) id;
        
        HobieEntity entityOpt = hobiePostgresRepository.findByHobieId(hobieId);
        if (entityOpt == null) {
            return null;
        }

        return hobieMapper.fromEntity(entityOpt);
    }

    @Override
    public HobieDTO getByName(String name) {
        HobieEntity entityOpt = hobiePostgresRepository.findByName(name);
        if (entityOpt != null) {
            return hobieMapper.fromEntity(entityOpt);
        }
        return null;
    }

    @Override
    public List<HobieDTO> getAll() {
        List<HobieDocument> documents = hobieMongoRepository.findAll();
        if (documents == null || documents.isEmpty()) {
            return List.of();
        }
        return hobieMapper.fromDocuments(documents);
    }

    @Override
    public void deleteHobie(int id) {
        Long hobieId = (long) id;
        
        if (!hobieMongoRepository.existsById(hobieId)) {
            throw new RuntimeException("Hobie with ID " + id + " not found");
        }
        
        try {
            hobiePostgresRepository.deleteByHobieId(hobieId);
            
            hobieMongoRepository.deleteById(hobieId);
            
        } catch (Exception e) {
            throw new RuntimeException("Error deleting hobie: " + e.getMessage());
        }
    }

    @Override
    public HobieDTO updateHobie(HobieDTO hobieDTO) {
        Long hobieId = hobieDTO.getHobieId();
        
        if (!hobieMongoRepository.existsById(hobieId)) {
            throw new RuntimeException("Hobie with ID " + hobieId + " not found");
        }
        
        try {
            HobieEntity entity = hobieMapper.toEntity(hobieDTO);
            HobieDocument document = hobieMapper.toDocument(hobieDTO);
            
            hobiePostgresRepository.save(entity);
            HobieDocument updatedDocument = hobieMongoRepository.save(document);
            
            return hobieMapper.fromDocument(updatedDocument);
            
        } catch (Exception e) {
            throw new RuntimeException("Error updating hobie: " + e.getMessage());
        }
    }

    @Override
    public List<HobieDTO> getByPerson(int personId) {
        return List.of();
    }
}

package co.edu.uptc.temufacebook.persistence.mapper;

import co.edu.uptc.temufacebook.domain.dto.EventDTO;
import co.edu.uptc.temufacebook.domain.dto.PersonDTO;
import co.edu.uptc.temufacebook.domain.dto.HobieDTO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MapperUtils {

    // Conversiones de IDs
    
    /**
     * Convierte una lista de PersonDTO a una lista de IDs
     */
    default List<Long> personsToIds(List<PersonDTO> persons) {
        if (persons == null) return null;
        return persons.stream()
                .map(PersonDTO::getPersonId)
                .collect(Collectors.toList());
    }

    /**
     * Convierte una lista de EventDTO a una lista de IDs
     */
    default List<Long> eventsToIds(List<EventDTO> events) {
        if (events == null) return null;
        return events.stream()
                .map(EventDTO::getEventId)
                .collect(Collectors.toList());
    }

    /**
     * Convierte una lista de HobieDTO a una lista de IDs
     */
    default List<Long> hobbiesToIds(List<HobieDTO> hobbies) {
        if (hobbies == null) return null;
        return hobbies.stream()
                .map(HobieDTO::getHobieId)
                .collect(Collectors.toList());
    }

    // Utilidades de String
    
    /**
     * Convierte nombres de eventos a una lista
     */
    default List<String> eventNamesToList(List<EventDTO> events) {
        if (events == null) return null;
        return events.stream()
                .map(EventDTO::getName)
                .collect(Collectors.toList());
    }

    /**
     * Convierte nombres de hobbies a una lista
     */
    default List<String> hobbyNamesToList(List<HobieDTO> hobbies) {
        if (hobbies == null) return null;
        return hobbies.stream()
                .map(HobieDTO::getName)
                .collect(Collectors.toList());
    }

}
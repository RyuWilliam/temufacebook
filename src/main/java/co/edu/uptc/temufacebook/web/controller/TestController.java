package co.edu.uptc.temufacebook.web.controller;

import co.edu.uptc.temufacebook.domain.dto.PersonDTO;
import co.edu.uptc.temufacebook.domain.dto.EventDTO;
import co.edu.uptc.temufacebook.domain.service.PersonService;
import co.edu.uptc.temufacebook.domain.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {

    private final PersonService personService;
    private final EventService eventService;

    public TestController(PersonService personService, EventService eventService) {
        this.personService = personService;
        this.eventService = eventService;
    }

    // ------------------------
    // PERSON ENDPOINTS
    // ------------------------
    @PostMapping("/persons")
    public PersonDTO createPerson(@RequestBody PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }

    @GetMapping("/persons/{id}")
    public PersonDTO getPersonById(@PathVariable int id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/persons/name/{name}")
    public PersonDTO getPersonByName(@PathVariable String name) {
        return personService.getPersonByName(name);
    }

    @GetMapping("/persons")
    public List<PersonDTO> getAllPersons() {
        return personService.getAllPersons();
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
    }

    @GetMapping("/persons/{id}/friends")
    public List<PersonDTO> getFriends(@PathVariable int id) {
        return personService.getFriends(id);
    }

    // ------------------------
    // EVENT ENDPOINTS
    // ------------------------
    @PostMapping("/events")
    public EventDTO createEvent(@RequestBody EventDTO eventDTO) {
        return eventService.createEvent(eventDTO);
    }

    @GetMapping("/events/{id}")
    public EventDTO getEventById(@PathVariable int id) {
        return eventService.getEventById(id);
    }


    @GetMapping("/events")
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable int id) {
        eventService.deleteEvent(id);
    }

    @PutMapping("/events")
    public EventDTO updateEvent(@RequestBody EventDTO eventDTO) {
        return eventService.updateEvent(eventDTO);
    }


}

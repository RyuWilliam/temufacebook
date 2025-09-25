package co.edu.uptc.temufacebook.web.controller;

import co.edu.uptc.temufacebook.domain.dto.EventDTO;
import co.edu.uptc.temufacebook.domain.repository.EventRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PostMapping
    public EventDTO save(@RequestBody EventDTO eventDTO) {
        return eventRepository.saveEvent(eventDTO);
    }

    @GetMapping("/{id}")
    public EventDTO getById(@PathVariable int id) {
        return eventRepository.getById(id);
    }

    @GetMapping
    public List<EventDTO> getAll() {
        return eventRepository.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        eventRepository.deleteEvent(id);
    }
}


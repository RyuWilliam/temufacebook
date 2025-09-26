package co.edu.uptc.temufacebook.web.controller;

import co.edu.uptc.temufacebook.domain.dto.EventDTO;
import co.edu.uptc.temufacebook.domain.dto.PersonDTO;
import co.edu.uptc.temufacebook.domain.dto.PlaceDTO;
import co.edu.uptc.temufacebook.domain.dto.HobieDTO;
import co.edu.uptc.temufacebook.domain.service.EventService;
import co.edu.uptc.temufacebook.domain.service.PersonService;
import co.edu.uptc.temufacebook.domain.repository.PlaceRepository;
import co.edu.uptc.temufacebook.domain.repository.HobieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventViewController {

    private final EventService eventService;
    private final PersonService personService;
    private final PlaceRepository placeRepository;
    private final HobieRepository hobieRepository;

    public EventViewController(EventService eventService, PersonService personService,
                              PlaceRepository placeRepository, HobieRepository hobieRepository) {
        this.eventService = eventService;
        this.personService = personService;
        this.placeRepository = placeRepository;
        this.hobieRepository = hobieRepository;
    }

    @GetMapping
    public String showEvents(Model model) {
        List<EventDTO> events = eventService.getAllEvents();
        List<PersonDTO> allPersons = personService.getAllPersons();
        List<PlaceDTO> allPlaces = placeRepository.getAll();
        List<HobieDTO> allHobbies = hobieRepository.getAll();

        model.addAttribute("events", events);
        model.addAttribute("event", new EventDTO());
        model.addAttribute("allPersons", allPersons != null ? allPersons : List.of());
        model.addAttribute("allPlaces", allPlaces != null ? allPlaces : List.of());
        model.addAttribute("allHobbies", allHobbies != null ? allHobbies : List.of());
        
        // Agregar flags para saber si hay datos disponibles
        model.addAttribute("hasPersons", allPersons != null && !allPersons.isEmpty());
        model.addAttribute("hasPlaces", allPlaces != null && !allPlaces.isEmpty());
        model.addAttribute("hasHobbies", allHobbies != null && !allHobbies.isEmpty());

        return "events";
    }

    @PostMapping
    public String saveEvent(@ModelAttribute EventDTO event, RedirectAttributes redirectAttributes) {
        try {
            if (event.getEventId() != null) {
                eventService.updateEvent(event);
                redirectAttributes.addFlashAttribute("successMessage", "Evento actualizado exitosamente");
            } else {
                eventService.createEvent(event);
                redirectAttributes.addFlashAttribute("successMessage", "Evento creado exitosamente");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al guardar el evento: " + e.getMessage());
        }
        return "redirect:/events";
    }

    @GetMapping("/{id}")
    public String showEventDetail(@PathVariable int id, Model model) {
        try {
            EventDTO event = eventService.getEventById(id);
            model.addAttribute("event", event);
            return "event-detail";
        } catch (Exception e) {
            return "redirect:/events";
        }
    }

    @GetMapping("/{id}/edit")
    public String editEvent(@PathVariable int id, Model model) {
        try {
            EventDTO event = eventService.getEventById(id);
            List<PersonDTO> allPersons = personService.getAllPersons();
            List<PlaceDTO> allPlaces = placeRepository.getAll();
            List<HobieDTO> allHobbies = hobieRepository.getAll();

            model.addAttribute("event", event);
            model.addAttribute("events", eventService.getAllEvents());
            model.addAttribute("allPersons", allPersons != null ? allPersons : List.of());
            model.addAttribute("allPlaces", allPlaces != null ? allPlaces : List.of());
            model.addAttribute("allHobbies", allHobbies != null ? allHobbies : List.of());
            
            // Agregar flags para saber si hay datos disponibles
            model.addAttribute("hasPersons", allPersons != null && !allPersons.isEmpty());
            model.addAttribute("hasPlaces", allPlaces != null && !allPlaces.isEmpty());
            model.addAttribute("hasHobbies", allHobbies != null && !allHobbies.isEmpty());
            
            return "events";
        } catch (Exception e) {
            return "redirect:/events";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteEvent(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            eventService.deleteEvent(id);
            redirectAttributes.addFlashAttribute("successMessage", "Evento eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el evento: " + e.getMessage());
        }
        return "redirect:/events";
    }

    @GetMapping("/search")
    public String searchEvents(@RequestParam String name, Model model) {
        try {
            List<EventDTO> events = eventService.getAllEvents().stream()
                    .filter(event -> event.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
            
            List<PersonDTO> allPersons = personService.getAllPersons();
            List<PlaceDTO> allPlaces = placeRepository.getAll();
            List<HobieDTO> allHobbies = hobieRepository.getAll();

            model.addAttribute("events", events);
            model.addAttribute("event", new EventDTO());
            model.addAttribute("allPersons", allPersons != null ? allPersons : List.of());
            model.addAttribute("allPlaces", allPlaces != null ? allPlaces : List.of());
            model.addAttribute("allHobbies", allHobbies != null ? allHobbies : List.of());
            model.addAttribute("searchTerm", name);
            
            // Agregar flags para saber si hay datos disponibles
            model.addAttribute("hasPersons", allPersons != null && !allPersons.isEmpty());
            model.addAttribute("hasPlaces", allPlaces != null && !allPlaces.isEmpty());
            model.addAttribute("hasHobbies", allHobbies != null && !allHobbies.isEmpty());

            return "events";
        } catch (Exception e) {
            return "redirect:/events";
        }
    }
}
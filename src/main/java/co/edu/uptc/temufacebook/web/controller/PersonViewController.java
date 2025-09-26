package co.edu.uptc.temufacebook.web.controller;

import co.edu.uptc.temufacebook.domain.dto.PersonDTO;
import co.edu.uptc.temufacebook.domain.dto.HobieDTO;
import co.edu.uptc.temufacebook.domain.service.PersonService;
import co.edu.uptc.temufacebook.domain.repository.HobieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonViewController {

    private final PersonService personService;
    private final HobieRepository hobieRepository;

    public PersonViewController(PersonService personService, HobieRepository hobieRepository) {
        this.personService = personService;
        this.hobieRepository = hobieRepository;
    }

    @GetMapping
    public String showPersons(Model model) {
        List<PersonDTO> persons = personService.getAllPersons();
        List<HobieDTO> allHobbies = hobieRepository.getAll();

        model.addAttribute("persons", persons != null ? persons : List.of());
        model.addAttribute("person", new PersonDTO());
        model.addAttribute("allPersons", persons != null ? persons : List.of());
        model.addAttribute("allHobbies", allHobbies != null ? allHobbies : List.of());
        
        // Agregar flags para saber si hay datos disponibles
        model.addAttribute("hasPersons", persons != null && !persons.isEmpty());
        model.addAttribute("hasHobbies", allHobbies != null && !allHobbies.isEmpty());

        return "persons";
    }

    @PostMapping
    public String savePerson(@ModelAttribute PersonDTO person, RedirectAttributes redirectAttributes) {
        try {
            personService.createPerson(person);
            redirectAttributes.addFlashAttribute("successMessage", "Persona creada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al guardar la persona: " + e.getMessage());
        }
        return "redirect:/persons";
    }

    @GetMapping("/{id}")
    public String showPersonDetail(@PathVariable int id, Model model) {
        try {
            PersonDTO person = personService.getPersonById(id);
            model.addAttribute("person", person);
            return "person-detail";
        } catch (Exception e) {
            return "redirect:/persons";
        }
    }

    @GetMapping("/{id}/friends")
    public String showPersonFriends(@PathVariable int id, Model model) {
        try {
            List<PersonDTO> friends = personService.getFriends(id);
            PersonDTO person = personService.getPersonById(id);
            model.addAttribute("friends", friends != null ? friends : List.of());
            model.addAttribute("person", person);
            model.addAttribute("hasFriends", friends != null && !friends.isEmpty());
            return "person-friends";
        } catch (Exception e) {
            return "redirect:/persons";
        }
    }

    @PostMapping("/{id}")
    public String deletePerson(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            personService.deletePerson(id);
            redirectAttributes.addFlashAttribute("successMessage", "Persona eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar la persona: " + e.getMessage());
        }
        return "redirect:/persons";
    }

    @GetMapping("/search")
    public String searchPersons(@RequestParam String name, Model model) {
        try {
            List<PersonDTO> allPersonsForSearch = personService.getAllPersons();
            List<PersonDTO> persons = allPersonsForSearch != null ? 
                allPersonsForSearch.stream()
                    .filter(person -> person.getName().toLowerCase().contains(name.toLowerCase()) ||
                                    person.getLastName().toLowerCase().contains(name.toLowerCase()))
                    .toList() : List.of();
            
            List<HobieDTO> allHobbies = hobieRepository.getAll();

            model.addAttribute("persons", persons);
            model.addAttribute("person", new PersonDTO());
            model.addAttribute("allPersons", allPersonsForSearch != null ? allPersonsForSearch : List.of());
            model.addAttribute("allHobbies", allHobbies != null ? allHobbies : List.of());
            model.addAttribute("searchTerm", name);
            
            // Agregar flags para saber si hay datos disponibles
            model.addAttribute("hasPersons", allPersonsForSearch != null && !allPersonsForSearch.isEmpty());
            model.addAttribute("hasHobbies", allHobbies != null && !allHobbies.isEmpty());

            return "persons";
        } catch (Exception e) {
            return "redirect:/persons";
        }
    }
}
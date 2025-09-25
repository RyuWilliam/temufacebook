package co.edu.uptc.temufacebook.web.controller;

import co.edu.uptc.temufacebook.domain.dto.PersonDTO;
import co.edu.uptc.temufacebook.domain.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public PersonDTO save(@RequestBody PersonDTO personDTO) {
        return personRepository.savePerson(personDTO);
    }

    @GetMapping("/{id}")
    public PersonDTO getById(@PathVariable int id) {
        return personRepository.getById(id);
    }

    @GetMapping
    public List<PersonDTO> getAll() {
        return personRepository.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        personRepository.deletePerson(id);
    }
}


package co.edu.uptc.temufacebook.web.controller;

import co.edu.uptc.temufacebook.domain.dto.HobieDTO;
import co.edu.uptc.temufacebook.domain.repository.HobieRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hobies")
public class HobieController {
    private final HobieRepository hobieRepository;

    public HobieController(HobieRepository hobieRepository) {
        this.hobieRepository = hobieRepository;
    }

    @PostMapping
    public HobieDTO save(@RequestBody HobieDTO hobieDTO) {
        return hobieRepository.saveHobie(hobieDTO);
    }

    @GetMapping("/{id}")
    public HobieDTO getById(@PathVariable int id) {
        return hobieRepository.getById(id);
    }

    @GetMapping
    public List<HobieDTO> getAll() {
        return hobieRepository.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        hobieRepository.deleteHobie(id);
    }
}


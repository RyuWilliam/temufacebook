package co.edu.uptc.temufacebook.web.controller;

import co.edu.uptc.temufacebook.domain.dto.PlaceDTO;
import co.edu.uptc.temufacebook.domain.repository.PlaceRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {
    private final PlaceRepository placeRepository;

    public PlaceController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @PostMapping
    public PlaceDTO save(@RequestBody PlaceDTO placeDTO) {
        return placeRepository.savePlace(placeDTO);
    }

    @GetMapping("/{id}")
    public PlaceDTO getById(@PathVariable int id) {
        return placeRepository.getById(id);
    }

    @GetMapping
    public List<PlaceDTO> getAll() {
        return placeRepository.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        placeRepository.deletePlace(id);
    }
}


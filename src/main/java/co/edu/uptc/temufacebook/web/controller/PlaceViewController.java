package co.edu.uptc.temufacebook.web.controller;

import co.edu.uptc.temufacebook.domain.dto.PlaceDTO;
import co.edu.uptc.temufacebook.domain.repository.PlaceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/places")
public class PlaceViewController {

    private final PlaceRepository placeRepository;

    public PlaceViewController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @GetMapping
    public String showPlaces(Model model) {
        List<PlaceDTO> places = placeRepository.getAll();
        model.addAttribute("places", places != null ? places : List.of());
        model.addAttribute("place", new PlaceDTO());
        
        // Agregar flag para saber si hay datos disponibles
        model.addAttribute("hasPlaces", places != null && !places.isEmpty());
        
        return "places";
    }

    @PostMapping
    public String savePlace(@ModelAttribute PlaceDTO place, RedirectAttributes redirectAttributes) {
        try {
            placeRepository.savePlace(place);
            redirectAttributes.addFlashAttribute("successMessage", "Lugar guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al guardar el lugar: " + e.getMessage());
        }
        return "redirect:/places";
    }

    @PostMapping("/{id}/delete")
    public String deletePlace(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            placeRepository.deletePlace(id);
            redirectAttributes.addFlashAttribute("successMessage", "Lugar eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el lugar: " + e.getMessage());
        }
        return "redirect:/places";
    }
}
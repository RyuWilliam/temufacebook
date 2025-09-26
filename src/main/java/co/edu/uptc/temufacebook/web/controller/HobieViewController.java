package co.edu.uptc.temufacebook.web.controller;

import co.edu.uptc.temufacebook.domain.dto.HobieDTO;
import co.edu.uptc.temufacebook.domain.repository.HobieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/hobbies")
public class HobieViewController {

    private final HobieRepository hobieRepository;

    public HobieViewController(HobieRepository hobieRepository) {
        this.hobieRepository = hobieRepository;
    }

    @GetMapping
    public String showHobbies(Model model) {
        List<HobieDTO> hobbies = hobieRepository.getAll();
        model.addAttribute("hobbies", hobbies != null ? hobbies : List.of());
        model.addAttribute("hobby", new HobieDTO());
        
        // Agregar flag para saber si hay datos disponibles
        model.addAttribute("hasHobbies", hobbies != null && !hobbies.isEmpty());
        
        return "hobbies";
    }

    @PostMapping
    public String saveHobby(@ModelAttribute HobieDTO hobby, RedirectAttributes redirectAttributes) {
        try {
            hobieRepository.saveHobie(hobby);
            redirectAttributes.addFlashAttribute("successMessage", "Hobby guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al guardar el hobby: " + e.getMessage());
        }
        return "redirect:/hobbies";
    }

    @PostMapping("/{id}/delete")
    public String deleteHobby(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            hobieRepository.deleteHobie(id);
            redirectAttributes.addFlashAttribute("successMessage", "Hobby eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el hobby: " + e.getMessage());
        }
        return "redirect:/hobbies";
    }
}
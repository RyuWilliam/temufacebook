package co.edu.uptc.temufacebook.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HobieDTO {
    private Long hobieId;
    private String name;
    private String description;
    private List<EventDTO> relatedEvents;
}
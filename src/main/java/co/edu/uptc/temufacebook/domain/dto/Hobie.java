package co.edu.uptc.temufacebook.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Hobie {
    private Integer hobieId;
    private String name;
    private String description;
    private List<Event> events;
}

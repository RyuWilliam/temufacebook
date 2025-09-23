package co.edu.uptc.temufacebook.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PlaceDTO {
    private Integer placeId;
    private String name;
    private String address;
    private List<String> events;
}
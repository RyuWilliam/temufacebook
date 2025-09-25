package co.edu.uptc.temufacebook.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlaceDTO {
    private Long placeId;
    private String name;
    private String address;
}
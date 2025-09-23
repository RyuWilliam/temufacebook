package co.edu.uptc.temufacebook.domain.dto;

import java.util.List;

public class Person {
    private Integer personId;
    private String name;
    private String lastName;
    private String phone;
    private List<Event> events;
    private List<Hobie> hobies;
    private List<Person> friends;
}

package co.edu.uptc.temufacebook.domain.dto;

import java.util.List;

public class PersonDTO {
    private Integer personId;
    private String name;
    private String lastName;
    private String phone;
    private List<String> events;
    private List<HobieDTO> hobies;
    private List<PersonDTO> friends;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public List<HobieDTO> getHobies() {
        return hobies;
    }

    public void setHobies(List<HobieDTO> hobies) {
        this.hobies = hobies;
    }

    public List<PersonDTO> getFriends() {
        return friends;
    }

    public void setFriends(List<PersonDTO> friends) {
        this.friends = friends;
    }
}

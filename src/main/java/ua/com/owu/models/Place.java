package ua.com.owu.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int placeId;
    private String name;
    private String adress;
    private String city;
    private String specification;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "place")
    List<Manager> managers;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "place")
    List<UserTable> userTables;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY ,mappedBy = "place")
    List<Event> events;



    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public List<UserTable> getUserTables() {
        return userTables;
    }

    public void setUserTables(List<UserTable> userTables) {
        this.userTables = userTables;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeId=" + placeId +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", city='" + city + '\'' +
                ", specification='" + specification + '\'' +
                '}';
    }
}

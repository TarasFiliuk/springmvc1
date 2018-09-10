package ua.com.owu.models;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Manager")
public class Manager extends Account {

    @Column(unique = true)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public Manager(String descr, Place place, List<UserOrder> userOrders, List<UserOrder> orders, Role role, String password, String username, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, String token, String firstName, String lastName) {
        super(descr, place, userOrders, orders, role, password, username, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
        this.token = token;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<UserOrder> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    private String firstName;

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
    }

    private String lastName;

    public String getSurname() {
        return lastName;
    }

    public void setSurname(String surname) {
        this.lastName = surname;
    }

}




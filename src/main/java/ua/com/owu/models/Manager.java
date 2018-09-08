package ua.com.owu.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Manager  extends Account{



    @Column(unique = true)
    private String token;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Place place;
    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "managers")
    List<UserOrder> userOrders;
    public List<UserOrder> getUserOrders() {
        return userOrders;
    }
    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }


    private String firstName;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    private String lastName;
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return "Manager{" +
                "token='" + token + '\'' +
                ", place=" + place +
                ", userOrders=" + userOrders +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}


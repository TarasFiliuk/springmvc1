package ua.com.owu.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("manager")
public class Manager  extends Account{

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Place place;

    public Manager() {
    }

    public Manager(Role role, String password, String username, String email) {
        super(role, password, username, email);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return Objects.equals(place, manager.place) &&
                Objects.equals(userOrders, manager.userOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, userOrders);
    }


}


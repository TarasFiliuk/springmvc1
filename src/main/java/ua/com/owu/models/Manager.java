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



}


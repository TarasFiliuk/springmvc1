package ua.com.owu.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("user")
public class User extends Account{

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserOrder> userOrders;
    public List<UserOrder> getUserOrders() {
        return userOrders;
    }
    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }



}

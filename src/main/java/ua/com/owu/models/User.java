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

    public User() {
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserOrder> userOrders;

    public User(Role role, String password, String username, String email) {
        super(role, password, username, email);
    }

    public List<UserOrder> getUserOrders() {
        return userOrders;
    }
    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }



}

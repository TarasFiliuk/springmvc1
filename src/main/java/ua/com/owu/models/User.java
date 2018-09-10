package ua.com.owu.models;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("User")
public class User extends Account {

    private String firstName;

    public User(/*UserOrder> orders, Role role, String password, String username, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled*/) {
//        super(descr, place, userOrders, orders, role, password, username, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
    }

    public User(String descr, Place place, List<UserOrder> userOrders, List<UserOrder> orders, Role role, String password, String username, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, String firstName, String lastName, String token) {
        super(descr, place, userOrders, orders, role, password, username, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
    }

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

    @Column(unique = true)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }





    public List<UserOrder> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }

    }

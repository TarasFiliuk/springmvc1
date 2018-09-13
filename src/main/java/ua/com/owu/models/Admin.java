package ua.com.owu.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Account{

    public Admin() {
    }

    public Admin(Role role, String password, String username, String email) {
        super(role, password, username, email);
    }
}



package ua.com.owu.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Admin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    @Column(unique = true)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return adminId;
    }
    public void setId(int id) {
        this.adminId = id;
    }
    @Column(unique = true)
    private String email;

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    private String firstName;
    public String getName() { return firstName; }
    public void setName(String name) { this.firstName = name; }

    private String lastName;
    public String getSurname() { return lastName; }
    public void setSurname(String surname) { this.lastName = surname; }



    private Role role = Role.ROLE_ADMIN;
    public void setRole(Role role) {
        this.role = role;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }



    private String password;
    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }



    private String username;
    @Override
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Admin() {

    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    public Role getRole() {
        return role;
    }

    private boolean isAccountNonExpired = true;
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }
    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }



    private boolean isAccountNonLocked=true;
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }
    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }


    private boolean isCredentialsNonExpired = true;
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }


    private boolean isEnabled = true;
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}



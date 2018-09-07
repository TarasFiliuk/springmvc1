package ua.com.owu.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.owu.models.User;

import java.util.List;
import java.util.Optional;


public interface UserService extends UserDetailsService {

    List<User> findAll();
    Optional<User> findById(int id);
    void save(User user);

}

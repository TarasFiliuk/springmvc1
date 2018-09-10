package ua.com.owu.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.UserDAO;
import ua.com.owu.models.User;
import ua.com.owu.service.userService.UserService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    public List<User> findAll() {
        return (List<User>) userDAO.findAll();
    }


    public User save(User user) {
        userDAO.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User byUsername = userDAO.findByUsername(s);
        return byUsername;
    }


    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }



}

package ua.com.owu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.UserDAO;
import ua.com.owu.models.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(userDAO.findOne(id));
    }

    public void save(User user) {
        userDAO.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User byUsername = userDAO.findByUsername(s);
        return byUsername;
    }

}

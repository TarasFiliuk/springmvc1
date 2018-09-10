package ua.com.owu.service.userService;


import ua.com.owu.models.User;
import ua.com.owu.service.AccountService.AccounBaseRepository;

import java.util.Optional;


public interface UserService extends AccounBaseRepository<User>{

    Iterable<User> findAll();
    User save(User user);

}

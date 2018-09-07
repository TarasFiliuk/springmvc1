package ua.com.owu.service.managerService;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.owu.models.Manager;

public interface ManagerService extends UserDetailsService {
    Manager findByFirstName(String firstname);
}

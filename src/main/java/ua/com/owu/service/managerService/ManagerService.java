package ua.com.owu.service.managerService;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.models.Manager;
import ua.com.owu.service.AccountService.AccounBaseRepository;
@Transactional
public interface ManagerService extends AccounBaseRepository<Manager> {
    Manager findByFirstName(String firstname);
}

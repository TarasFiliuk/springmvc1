package ua.com.owu.service.AccountService;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.owu.models.Account;
import ua.com.owu.models.Role;

import java.util.List;

public interface AccountService  extends UserDetailsService{
    void save(Account account);
    Account findbyId(int id);
    List<Account> findByRole (Role role);
    List<Account> findAll();
}

package ua.com.owu.service.AccountService;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ua.com.owu.models.Account;
import ua.com.owu.models.Role;
import java.util.List;
public interface AccountService  extends UserDetailsService{
    void save(Account account);
    Account findById(int id);
    List<Account>findByAccountType(String accountType);
    List<Account> findAll();
    Account findByEmail(String email);
    Account findByToken (String token);
    Account findByUsername(String username);

}

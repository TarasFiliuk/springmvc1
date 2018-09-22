package ua.com.owu.service.accountService;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.owu.models.Account;
import java.util.List;

public interface AccountService  extends UserDetailsService{
    void save(Account account);
    Account findById(int id);
    List<Account>findByAccountType(String accountType);
    List<Account> findAll();
    Account findByEmail(String email);
    Account findByToken (String token);
    Account findByUsername(String username);
    void deleteById(int id);
    void update(int id, Account account);

}

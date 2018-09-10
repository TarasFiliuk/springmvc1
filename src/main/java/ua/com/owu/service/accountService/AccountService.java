package ua.com.owu.service.accountService;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.owu.models.Account;

public interface AccountService extends UserDetailsService {
    void save(Account account);
    Account findById(int id);

}

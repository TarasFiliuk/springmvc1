package ua.com.owu.service.AccountService;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.owu.models.Account;

@NoRepositoryBean
public interface AccounBaseRepository<T extends Account> extends UserDetailsService {
    T findById(int id);

}

package ua.com.owu.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.AccountDAO;
import ua.com.owu.models.Account;
import ua.com.owu.models.Role;

import java.util.List;

@Service
@Transactional
@Component("accoutServiceImpl")
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO accountDAO;

    @Override
    public void save(Account account) {
        accountDAO.save(account);
    }

    @Override
    public Account findbyId(int id) {
        return accountDAO.findOne(id);
    }

    @Override
    public List<Account> findByAccountType(String accountType) {
        return accountDAO.findByAccountType(accountType);
    }


    @Override
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Override
    public Account findByEmail(String email) {
        return accountDAO.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {

        if (param.contains("@")) {
            return accountDAO.findByEmail(param);
        } else
            return accountDAO.findByUsername(param);
    }

    @Override
    public Account findByToken(String token) {
        return accountDAO.findByToken(token);

    }

    @Override
    public Account findByUsername(String username) {
        return accountDAO.findByUsername(username);
    }
}

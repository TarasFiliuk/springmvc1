package ua.com.owu.service.accountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.AccountDAO;
import ua.com.owu.models.Account;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final
    AccountDAO accountDAO;

    @Autowired
    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    @Transactional
    public void save(Account account) {
        accountDAO.save(account);
    }

    @Override
    @Transactional
    public Account findById(int id) {
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

    @Transactional
    public Account findByToken(String token) {
        return accountDAO.findByToken(token);

    }

    @Override
    @Transactional
    public Account findByUsername(String username) {
        return accountDAO.findByUsername(username);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        accountDAO.delete(id);
    }

    @Override
    public void update(int id, Account account) {
        Account one = accountDAO.findOne(id);
    }
}

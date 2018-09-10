package ua.com.owu.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.AccountDAO;
import ua.com.owu.models.Account;
import ua.com.owu.models.Role;

import java.util.List;

@Service
@Transactional
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
    public List<Account> findByRole(Role role) {
        return accountDAO.findByRole(role);
    }


    @Override
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountDAO.findByUsername(username);
    }
}
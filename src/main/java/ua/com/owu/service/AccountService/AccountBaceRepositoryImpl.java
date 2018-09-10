package ua.com.owu.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.AccountDAO;
import ua.com.owu.dao.ManagerDAO;
import ua.com.owu.dao.UserDAO;
import ua.com.owu.models.Account;
@Service
@Transactional
public class AccountBaceRepositoryImpl implements AccounBaseRepository<Account> {
    @Autowired
    AccountDAO accountDAO;
    @Override
    public Account findById(int id) {
        return accountDAO.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountDAO.findByUsername(username);
    }
}

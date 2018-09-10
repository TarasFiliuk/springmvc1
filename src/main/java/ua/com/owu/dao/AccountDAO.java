package ua.com.owu.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.Account;
import ua.com.owu.models.Role;

import java.util.List;

@Repository
public interface AccountDAO extends JpaRepository<Account,Integer> {
    Account findByUsername(String username);
     List<Account>findByRole (Role role);

}

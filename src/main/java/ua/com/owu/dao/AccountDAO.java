package ua.com.owu.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account,Integer> {
    Account findByUsername(String username);
}

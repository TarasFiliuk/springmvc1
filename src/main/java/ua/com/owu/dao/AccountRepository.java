package ua.com.owu.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.Account;

@Repository
public interface AccountRepository<T extends Account> extends JpaRepository<T,Integer> {

}

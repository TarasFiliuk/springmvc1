package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.Account;
import ua.com.owu.models.Manager;
import ua.com.owu.models.User;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface AccountDAO<T extends Account> extends CrudRepository<T, Integer> {
    T findById(int id);
    T findByUsername(String username);



}

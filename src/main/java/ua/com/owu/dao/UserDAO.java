package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.User;

@Repository
public interface UserDAO extends AccountDAO<User> {
    User findByUsername(String s);
}

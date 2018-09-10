package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.User;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User findByToken(String token);
}

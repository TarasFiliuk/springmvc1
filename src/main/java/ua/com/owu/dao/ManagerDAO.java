package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.Manager;
@Repository
public interface ManagerDAO extends JpaRepository<Manager,Integer> {
    Manager findByUsername (String username);
    Manager findByFirstName(String firstname);
}

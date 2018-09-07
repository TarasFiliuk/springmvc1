package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.Admin;
import ua.com.owu.models.User;
@Repository
public interface AdminDAO extends JpaRepository<Admin,Integer> {

}

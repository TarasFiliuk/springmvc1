package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.UserTable;
@Repository
public interface UserTableDAO extends JpaRepository<UserTable,Integer> {
    UserTable findByCapacity (int capacity);
    UserTable findByTableId (int idTable);
}

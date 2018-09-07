package ua.com.owu.service.userTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.UserTableDAO;
import ua.com.owu.models.UserTable;
@Service
@Transactional
public class UserTableServiceImpl implements UserTableService {
    @Autowired
    UserTableDAO userTableDAO;
    @Override
    public UserTable findByCapacity(int capacity) {
        return userTableDAO.findByCapacity(capacity);
    }

    @Override
    public UserTable findByTableId(int idTable) {
        return userTableDAO.findByTableId(idTable);
    }
}

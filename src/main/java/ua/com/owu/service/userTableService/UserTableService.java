package ua.com.owu.service.userTableService;

import ua.com.owu.models.UserTable;

public interface UserTableService {
    UserTable findByCapacity (int capacity);
    UserTable findByTableId (int idTable);
}

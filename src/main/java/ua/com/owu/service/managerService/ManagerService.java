package ua.com.owu.service.managerService;

import ua.com.owu.models.Manager;

public interface ManagerService {
    Manager findByUsername (String username);
    Manager findByFirstName(String firstname);
}

package ua.com.owu.service;

import ua.com.owu.models.Manager;

public interface ManagerService {
    Manager findByUsername (String username);
    Manager findByFirstName(String firstname);
}

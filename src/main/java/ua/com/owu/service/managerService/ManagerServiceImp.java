package ua.com.owu.service.managerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.owu.dao.ManagerDAO;
import ua.com.owu.models.Manager;
import ua.com.owu.service.managerService.ManagerService;

@Service
public class ManagerServiceImp implements ManagerService {
    @Autowired
    ManagerDAO managerDAO;
    @Override
    public Manager findByUsername(String username) {
        return managerDAO.findByUsername(username);
    }

    @Override
    public Manager findByFirstName(String firstname) {
        return managerDAO.findByFirstName(firstname);
    }
}

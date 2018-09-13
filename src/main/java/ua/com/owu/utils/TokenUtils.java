package ua.com.owu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.owu.dao.AccountDAO;
import ua.com.owu.models.Account;

import java.util.Date;
import java.util.UUID;

public class TokenUtils {
    @Autowired
    AccountDAO accountDAO;

    public String generateToken(){
        String uuidtoken = UUID.randomUUID().toString().replace("-", "");
        return uuidtoken;
    }



}

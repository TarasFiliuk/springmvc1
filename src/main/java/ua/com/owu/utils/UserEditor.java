package ua.com.owu.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.com.owu.models.User;

import java.beans.PropertyEditorSupport;

@Component
public class UserEditor extends PropertyEditorSupport {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void setValue(Object value) {
        User user = (User) value;
        String password = user.getPassword();
        String encode = passwordEncoder.encode(password);
        user.setPassword(encode);
    }
}

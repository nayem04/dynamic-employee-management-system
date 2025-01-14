package com.dynamicemployeemanagementsystem.common.utilities;

import com.dynamicemployeemanagementsystem.common.exceptions.NullPasswordException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class PasswordUtil {
    public static BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static String encryptPassword(String password) throws NullPasswordException {
        if (password == null) throw ExceptionUtil.getNullPasswordException();
        return getBCryptPasswordEncoder().encode(password);
    }

    public static boolean matches(String existingPassword, String password) throws NullPasswordException {
        if (existingPassword == null || password == null) throw ExceptionUtil.getNullPasswordException();
        return getBCryptPasswordEncoder().matches(password, existingPassword);
    }
}

package com.administration.api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class FunctionUtils {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(AppConstants.BCRYPT_STRENTGTH_12);

    public String encodePassword(String password) {
        return encoder.encode(password);
    }
}

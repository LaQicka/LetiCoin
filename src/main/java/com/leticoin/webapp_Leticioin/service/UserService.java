package com.leticoin.webapp.service;

import com.leticoin.webapp.controller.dto.UserRegistrationDto;
import com.leticoin.webapp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Service class for User
 * @author Sergei Mazin
 * @version 0.4
 */

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}

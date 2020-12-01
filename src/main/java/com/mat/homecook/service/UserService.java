package com.mat.homecook.service;

import com.mat.homecook.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.mat.homecook.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}

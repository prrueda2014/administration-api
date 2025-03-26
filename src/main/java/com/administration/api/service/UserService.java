package com.administration.api.service;

import com.administration.api.model.Users;
import com.administration.api.repo.UserRepo;
import com.administration.api.util.FunctionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    AuthenticationManager authManger;

    @Autowired
    JWTService JWTService;

    @Autowired
    FunctionUtils functionUtils;

    public Users registerUser(Users user) {
        user.setPassword(functionUtils.encodePassword(user.getPassword()));
        return userRepo.save(user);
    }

    public Optional<Users> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<Users> updateUser(String username, Users userRequest) {
        Optional<Users> user = userRepo.findByUsername(username);
        if (user.isPresent()) {
            user.get().setUsername(userRequest.getUsername());
            user.get().setPassword(functionUtils.encodePassword(userRequest.getPassword()));
            user.get().setRole(userRequest.getRole());
            user.get().setName(userRequest.getName());
            userRepo.save(user.get());
        }
        return user;

    }

    public void deleteUser(String username) {
        Optional<Users> user = userRepo.findByUsername(username);
        user.ifPresent(users -> userRepo.delete(users));
    }

    public String verifyUser(Users user) {
        Authentication authentication = authManger.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return JWTService.generateToken(user.getUsername());
        }
        return "Fail";
    }
}

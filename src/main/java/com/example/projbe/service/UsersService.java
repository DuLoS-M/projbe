package com.example.projbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projbe.entity.Users;
import com.example.projbe.repository.UsersRepository;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    public Users updateUser(Long id, Users userDetails) {
        Users user = usersRepository.findById(id).orElse(null);
        if (user != null) {
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setNit(userDetails.getNit());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setRole(userDetails.getRole());
            return usersRepository.save(user);
        }
        return null;
    }
    

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

     public Users validateUser(String email, String password, String role) {
        Users user = usersRepository.findByEmail(email);
        System.out.println(user);
        System.out.println(user.getRole());
        System.out.println(role);
        if (user != null && user.getPassword().equals(password) && user.getRole().toString().equals(role)) {
            return user;
        }
        System.out.println("Invalid credentials");
        return null;
    }
}
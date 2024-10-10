package com.example.projbe.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projbe.entity.Users;
import com.example.projbe.entity.Users.Role;
import com.example.projbe.repository.UsersRepository;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

       private static final Logger logger = Logger.getLogger(UsersService.class.getName());

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
        Role roleEnum = Role.valueOf(role.toUpperCase());
        logger.info("Validating user with email: " + email + ", role: " + role);
        Users user = usersRepository.findByEmail(email);
        if (user != null) {
            logger.info("User found: " + user.getEmail());
            logger.info("User role: " + user.getRole());
            logger.info("Role: " + role);
            logger.info("User password: " + user.getPassword());
            logger.info("Role enum: " + roleEnum);
            logger.info("Password: " + password);

            if (user.getPassword().equals(password) && user.getRole().equals(roleEnum)) {
                logger.info("User validated successfully");
                return user;
            } else {
                logger.warning("Invalid password or role for user: " + email);
            }
        } else {
            logger.warning("User not found with email: " + email);
        }
        return null;
    }

        public Users updatePassword(Long id, String password) {
        Users user = usersRepository.findById(id).orElse(null);
        if (user != null) {
            user.setPassword(password);
            usersRepository.save(user);
            return user;
        }
        return null;
    }
}
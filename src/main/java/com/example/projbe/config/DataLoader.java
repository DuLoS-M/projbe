// package com.example.projbe.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import com.example.projbe.entity.Users;
// import com.example.projbe.entity.Users.Role;
// import com.example.projbe.repository.UsersRepository;

// @Component
// public class DataLoader implements CommandLineRunner {

//     @Autowired
//     private UsersRepository usersRepository;

//     @Override
//     public void run(String... args) throws Exception {
//         // Create test users
//         Users user1 = new Users();
//         user1.setFirstName("John");
//         user1.setLastName("Doe");
//         user1.setNit("123456789");
//         user1.setEmail("admin@admin.com");
//         user1.setPassword("password123");
//         user1.setRole(Role.ADMIN);

//         Users user2 = new Users();
//         user2.setFirstName("Jane");
//         user2.setLastName("Smith");
//         user2.setNit("987654321");
//         user2.setEmail("jane.smith@example.com");
//         user2.setPassword("password456");
//         user2.setRole(Role.CUSTOMER);

//         // Save users to the database
//         usersRepository.save(user1);
//         usersRepository.save(user2);
//     }
// }
package com.example.projbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projbe.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
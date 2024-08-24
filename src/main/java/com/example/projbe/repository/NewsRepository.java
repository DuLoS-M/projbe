package com.example.projbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projbe.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
package com.example.projbe.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.projbe.entity.News;
import com.example.projbe.repository.NewsRepository;
import com.example.projbe.service.FileStorageService;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private NewsRepository newsRepository;

    @PostMapping
    public ResponseEntity<News> createNews(@RequestParam("title") String title,
                                           @RequestParam("content") String content,
                                           @RequestParam(value = "photoUrl", required = false) MultipartFile photoUrl) {
        News news = new News();
        news.setTitle(title);
        news.setContent(content);

        if (photoUrl != null && !photoUrl.isEmpty()) {
            try {
                String fileName = fileStorageService.storeFile(photoUrl);
                news.setPhotoUrl("http://localhost:8080/uploads/" + fileName); // Ensure this path is correct
            } catch (IOException e) {
                return ResponseEntity.status(500).build();
            }
        }

        newsRepository.save(news);
        return ResponseEntity.ok(news);
    }

    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        Optional<News> news = newsRepository.findById(id);
        return news.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable Long id,
                                           @RequestParam("title") String title,
                                           @RequestParam("content") String content,
                                           @RequestParam(value = "photoUrl", required = false) MultipartFile photoUrl) {
        Optional<News> existingNews = newsRepository.findById(id);
        if (!existingNews.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        News news = existingNews.get();
        news.setTitle(title);
        news.setContent(content);

        if (photoUrl != null && !photoUrl.isEmpty()) {
            try {
                String fileName = fileStorageService.storeFile(photoUrl);
                news.setPhotoUrl("http://localhost:8080/uploads/" + fileName); // Ensure this path is correct
            } catch (IOException e) {
                return ResponseEntity.status(500).build();
            }
        }

        newsRepository.save(news);
        return ResponseEntity.ok(news);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        if (!newsRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        newsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
package com.example.projbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projbe.entity.News;
import com.example.projbe.repository.NewsRepository;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News getNewsById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    public News createNews(News news) {
        return newsRepository.save(news);
    }

    public News updateNews(Long id, News newsDetails) {
        News news = newsRepository.findById(id).orElse(null);
        if (news != null) {
            news.setTitle(newsDetails.getTitle());
            news.setContent(newsDetails.getContent());
            news.setPhotoUrl(newsDetails.getPhotoUrl());
            news.setUpdatedAt(newsDetails.getUpdatedAt());
            return newsRepository.save(news);
        }
        return null;
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
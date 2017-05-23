package com.github.jlran.news;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.github.jlran.vo.News;

@Transactional
public class NewsService {
	private NewsDao newsDao;
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	public void save(News news) {
		newsDao.save(news);
		
	}
	public List<News> findAll() {
		return newsDao.findAll();
	}
	public List<News> findLimitNews() {
		return newsDao.findLimitNews();
	}
	public News findById(int id) {
		return newsDao.findById(id);
	}
	
	
}

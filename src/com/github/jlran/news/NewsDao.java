package com.github.jlran.news;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.github.jlran.vo.News;

public class NewsDao extends HibernateDaoSupport{

	public void save(News news) {
		this.getHibernateTemplate().save(news);
	}

	public List<News> findAll() {
		List<News> list = this.getHibernateTemplate().find("from News");
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	public List<News> findLimitNews() {
		////select * from msg order by id desc limit n ;  
		List<News> list = this.getHibernateTemplate().find("from News order by id desc limit 0,10");
		return list;
	}

	public News findById(int id) {
		List<News> list = this.getHibernateTemplate().find("from News where id=?", id);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}	
}

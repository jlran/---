package com.github.jlran.tools;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.github.jlran.vo.News;
import com.github.jlran.vo.Tools;

public class ToolsDao extends HibernateDaoSupport{

	public void save(Tools tools) {
		this.getHibernateTemplate().save(tools);
	}

	public List<Tools> findAll() {
		List<Tools> list = this.getHibernateTemplate().find("from Tools");
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	public List<Tools> findLimitNews() {
		//select * from msg order by id desc limit n ;  
		List<Tools> list = this.getHibernateTemplate().find("from Tools order by id desc limit 0,10");
		return list;
	}

	public Tools findById(int id) {
		List<Tools> list = this.getHibernateTemplate().find("from Tools where id=?", id);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public List<Tools> findByUUID(String uuid) {
		List<Tools> list = this.getHibernateTemplate().find("from Tools where publicuuid=?", uuid);
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	public void delete(Tools tools) {
		this.getHibernateTemplate().delete(tools);
	}

}

package com.github.jlran.face;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.github.jlran.vo.Face;

public class FaceDao extends HibernateDaoSupport{

	public void save(Face face) {
		this.getHibernateTemplate().save(face);
	}

	public Face search(String uuid) {
		List<Face> list = this.getHibernateTemplate().find("from Face where uuid=?",uuid);
		if(list.size() > 0 ){
			return list.get(0);
		}
		return null;
	}

	public List<Face> findAll() {
		return this.getHibernateTemplate().find("from Face");
	}

	public Face findByQQ(String username) {
		List<Face> list =  this.getHibernateTemplate().find("from Face where uuid=?",username);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public void update(Face face) {
		this.getHibernateTemplate().update(face);
	}
	
}

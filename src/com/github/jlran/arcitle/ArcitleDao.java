package com.github.jlran.arcitle;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.github.jlran.vo.Arcitle;

public class ArcitleDao extends HibernateDaoSupport{

	public void save(Arcitle arcitle) {
		this.getHibernateTemplate().save(arcitle);
	}

	//查找所有的博文
	public List<Arcitle> findAll() {
		List<Arcitle> list = this.getHibernateTemplate().find("from Arcitle");
		if(list.size() >0){
			return list;
		}
		return null;
	}

	public List<Arcitle> findLimit(int i, int j) {
		return null;
	}

	public List<Arcitle> findLimitNews() {
		//select * from msg order by id desc limit n ;  
		List<Arcitle> list = this.getHibernateTemplate().find("from Arcitle order by id desc limit 0,10");
		return list;
	}

	public Arcitle findById(Integer id) {
		List<Arcitle> list = this.getHibernateTemplate().find("from Arcitle where id=?",id);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public List<Arcitle> findByLimit(String string, int index) {
		String sql = "from Arcitle where aclass='"+string+"' order by id desc limit "+index+",100";
		List<Arcitle> list = this.getHibernateTemplate().find(sql);
		return list;
	}

	public List<Arcitle> findByUUID(String publicuuid) {
		List<Arcitle> list = this.getHibernateTemplate().find("from Arcitle where publicuuid=? order by id desc",publicuuid);
		return list;
	}

	public void deleteById(Arcitle arcitle) {
		this.getHibernateTemplate().delete(arcitle);
	}
}

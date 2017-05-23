package com.github.jlran.jianli;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.github.jlran.vo.Jianli;

public class JianliDao extends HibernateDaoSupport{

	public Jianli find() {
		List<Jianli> list = this.getHibernateTemplate().find("from Jianli");
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public void update(Jianli jianli) {
		this.getHibernateTemplate().update(jianli);
	}
}

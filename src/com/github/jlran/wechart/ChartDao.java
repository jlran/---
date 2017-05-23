package com.github.jlran.wechart;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.github.jlran.wechart.vo.Chartnews;
import com.github.jlran.wechart.vo.Charttable;

public class ChartDao extends HibernateDaoSupport{

	//����������
	public void createTable(Charttable charttable) {
		this.getHibernateTemplate().save(charttable);
	}

	//���ݿⱣ���¼
	public void saveNews(Chartnews chartnews) {
		this.getHibernateTemplate().save(chartnews);
	}

	public List<Chartnews> findNewsLimit(String chartid) {
		String sql = "from Chartnews where chartid='"+chartid+"' order by id desc limit 20";
		List<Chartnews> list = this.getHibernateTemplate().find(sql);
		return list;
	}
	
}

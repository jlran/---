package com.github.jlran.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.github.jlran.vo.Arcitle;
import com.github.jlran.vo.User;

public class UserDao extends HibernateDaoSupport {

	//根据QQ查询用户
	public User findUserByQq(String qq) {
		List<User> list =this.getHibernateTemplate().find("from User where qq=?",qq);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	//注册用户
	public void Save(User user) {
		this.getHibernateTemplate().save(user);		
	}

	public User Login(User user) {
		List<User> list = this.getHibernateTemplate().find("from User where qq=? and password=?", user.getQq(), user.getPassword());
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public User findUserByUUID(String publicuuid) {
		List<User> list = this.getHibernateTemplate().find("from User where uuid=?", publicuuid);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public List<User> findUserAll() {
		List<User> list = this.getHibernateTemplate().find("from User");
		return list;
	}

	public void update(User user) {
		this.getHibernateTemplate().update(user);
	}

	public void delete(User user) {
		this.getHibernateTemplate().delete(user);
		Arcitle arcitle = new Arcitle();
		arcitle.setPublicuuid(user.getUuid());
		List<Arcitle> list = this.getHibernateTemplate().find("from Arcitle where publicuuid=?",user.getUuid());
		for (Arcitle li : list) {
			this.getHibernateTemplate().delete(li);
		}
	}	
}

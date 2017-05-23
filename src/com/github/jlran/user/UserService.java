package com.github.jlran.user;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.github.jlran.vo.User;

@Transactional
public class UserService {
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public User findUserByQq(String qq) {
		return userDao.findUserByQq(qq);
	}
	public void Save(User user) {
		userDao.Save(user);
	}
	public User Login(User user) {
		return userDao.Login(user);
	}
	public User findUserByUUID(String publicuuid) {
		return userDao.findUserByUUID(publicuuid);
	}
	public List<User> findUserAll() {
		return userDao.findUserAll();
	}
	public void update(User user) {
		userDao.update(user);
	}
	public void delete(User user) {
		userDao.delete(user);
	}
	
	
}

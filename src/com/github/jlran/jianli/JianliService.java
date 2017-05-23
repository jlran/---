package com.github.jlran.jianli;

import com.github.jlran.vo.Jianli;

public class JianliService {
	private JianliDao jianliDao;
	public void setJianliDao(JianliDao jianliDao) {
		this.jianliDao = jianliDao;
	}
	public Jianli find() {
		return jianliDao.find();
	}
	public void save(Jianli jianli) {
		jianliDao.update(jianli);
	}
	
}

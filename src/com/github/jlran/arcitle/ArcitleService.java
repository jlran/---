package com.github.jlran.arcitle;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.github.jlran.vo.Arcitle;

@Transactional
public class ArcitleService {
	private ArcitleDao arcitleDao;
	public void setArcitleDao(ArcitleDao arcitleDao) {
		this.arcitleDao = arcitleDao;
	}
	public void save(Arcitle arcitle) {
		arcitleDao.save(arcitle);
	}
	public List<Arcitle> findAll() {
		return arcitleDao.findAll();
	}
	//分页查询
	public List<Arcitle> findLimit(int i, int j) {
		return arcitleDao.findLimit(i,j);
	}
	//查找最新的几条记录
	public List<Arcitle> findLimitNews() {
		return arcitleDao.findLimitNews();
	}
	public Arcitle findById(Integer id) {
		return arcitleDao.findById(id);
	}
	public List<Arcitle> findByItLimit(String string, int index) {
		return arcitleDao.findByLimit(string,index);
	}
	public List<Arcitle> findByUUID(String publicuuid) {
		return arcitleDao.findByUUID(publicuuid);
	}
	public void deleteByid(Arcitle arcitle) {
		arcitleDao.deleteById(arcitle);
	}

}

package com.github.jlran.face;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.github.jlran.vo.Face;

@Transactional
public class FaceService {
	private FaceDao faceDao;
	public void setFaceDao(FaceDao faceDao) {
		this.faceDao = faceDao;
	}
	public void save(Face face) {
		faceDao.save(face);
	}
	public Face seach(String uuid) {
		return faceDao.search(uuid);
	}
	public List<Face> findAll() {
		return faceDao.findAll();
	}
	public Face findByQQ(String username) {
		return faceDao.findByQQ(username);
	}
	public void update(Face face) {
		faceDao.update(face);
	}
	
	
}

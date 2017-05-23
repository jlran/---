package com.github.jlran.tools;

import java.util.List;

import com.github.jlran.vo.News;
import com.github.jlran.vo.Tools;

public class ToolsService {
	private ToolsDao toolsDao;
	public void setToolsDao(ToolsDao toolsDao) {
		this.toolsDao = toolsDao;
	}
	public void save(Tools tools) {
		toolsDao.save(tools);
		
	}
	public List<Tools> findAll() {
		return toolsDao.findAll();
	}
	public List<Tools> findLimitNews() {
		
		return toolsDao.findLimitNews();
	}
	public Tools findById(int id) {
		return toolsDao.findById(id);
	}
	public List<Tools> findByUUID(String uuid) {
		return toolsDao.findByUUID(uuid);
	}
	public void delete(Tools tools) {
		toolsDao.delete(tools);
	}
}

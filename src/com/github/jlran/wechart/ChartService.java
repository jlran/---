package com.github.jlran.wechart;

import java.util.List;

import com.github.jlran.wechart.vo.Chartnews;
import com.github.jlran.wechart.vo.Charttable;

public class ChartService {
	private ChartDao chartDao;
	public void setChartDao(ChartDao chartDao) {
		this.chartDao = chartDao;
	}
	public void creatTable(Charttable charttable) {
		chartDao.createTable(charttable);
	}
	public void saveNews(Chartnews chartnews) {
		chartDao.saveNews(chartnews);
	}
	public List<Chartnews> findNewsLimit(String chartid) {
		return chartDao.findNewsLimit(chartid);
	}
	
	
}

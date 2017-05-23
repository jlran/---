package com.github.jlran.video;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.github.jlran.vo.Video;

public class VideoDao extends HibernateDaoSupport{

	public Video findVideo() {
		List<Video> list = this.getHibernateTemplate().find("from Video");
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public void update(Video video) {
		this.getHibernateTemplate().update(video);
	}

}

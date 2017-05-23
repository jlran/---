package com.github.jlran.video;

import com.github.jlran.vo.Video;

public class VideoService {
	private VideoDao videoDao;
	public void setVideoDao(VideoDao videoDao) {
		this.videoDao = videoDao;
	}
	public Video findVideo() {
		return videoDao.findVideo();
	}
	public void update(Video video) {
		videoDao.update(video);
	}
	
}

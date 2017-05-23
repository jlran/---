package com.github.jlran.video;

import org.apache.struts2.ServletActionContext;

import com.github.jlran.vo.Video;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class VideoAction extends ActionSupport implements ModelDriven<Video>{

	private Video video = new Video();
	@Override
	public Video getModel() {
		return video;
	}
	
	private VideoService videoService;
	public void setVideoService(VideoService videoService) {
		this.videoService = videoService;
	}
	
	public String show(){
		Video video = videoService.findVideo();
		String str = video.getUrl();
		ServletActionContext.getRequest().getSession().setAttribute("video", str);
		return "showSuccess";
	}
	
	public String update(){
		if(video != null){
			video.setId(1);
			videoService.update(video);
		}
		return "updateSuccess";
	}
	
}

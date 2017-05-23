package com.github.jlran.news;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.github.jlran.vo.Detail;
import com.github.jlran.vo.News;
import com.github.jlran.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class NewsAction extends ActionSupport implements ModelDriven<News>{

	private News news = new News();
	@Override
	public News getModel() {
		return news;
	}
	
	private NewsService newsService;
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	
	public String addNews(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user != null){
			if(user.getPermiss() != 0){
				//保存
				news.setData(new Date().toString());
				newsService.save(news);
				return "addNewsSuccess";
			}
		}
		return "Input";
	}
	
	public String show(){
		if(news != null){
			News eanews = newsService.findById(news.getId());
			if(eanews != null){
				Detail de = new Detail();
				de.setAclass("消息");
				de.setAuthor("微之炫平台管理员");
				de.setData(eanews.getData());
				de.setId(eanews.getId());
				de.setText(eanews.getText());
				de.setTitle(eanews.getTitle());
				de.setUsername("微之炫平台管理员");
				ServletActionContext.getRequest().getSession().setAttribute("detail", de);
				return "showSuccess";
			}
		}
		return "showInpu";
	}
	
}

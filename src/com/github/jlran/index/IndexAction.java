package com.github.jlran.index;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.github.jlran.arcitle.ArcitleService;
import com.github.jlran.news.NewsService;
import com.github.jlran.tools.ToolsService;
import com.github.jlran.vo.Arcitle;
import com.github.jlran.vo.News;
import com.github.jlran.vo.Tools;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	private ArcitleService arcitleService;
	private NewsService newsService;
	private ToolsService toolsService;
	public void setArcitleService(ArcitleService arcitleService) {
		this.arcitleService = arcitleService;
	}
	
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	public void setToolsService(ToolsService toolsService) {
		this.toolsService = toolsService;
	}
	
	public String index() throws Exception {
		
		//查找最后的十条记录
		List<Arcitle> articleslimit =  arcitleService.findLimitNews();
		List<News> newslimit = newsService.findLimitNews();
		List<Tools> toolslimit = toolsService.findLimitNews();
		
		HttpSession session = ServletActionContext.getRequest().getSession();		
		session.setAttribute("articleslimitnews", articleslimit);
		session.setAttribute("newslimitnews", newslimit);
		session.setAttribute("toolslimitnews", toolslimit);				
		return "none";
	}
}

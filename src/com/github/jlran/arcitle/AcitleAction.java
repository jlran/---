package com.github.jlran.arcitle;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.dao.DataAccessResourceFailureException;

import com.github.jlran.user.UserService;
import com.github.jlran.vo.Arcitle;
import com.github.jlran.vo.Detail;
import com.github.jlran.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AcitleAction extends ActionSupport implements ModelDriven<Arcitle>{
	private Arcitle arcitle = new Arcitle();	
	@Override
	public Arcitle getModel() {
		return arcitle;
	}
	
	private ArcitleService arcitleService;
	public void setArcitleService(ArcitleService arcitleService) {
		this.arcitleService = arcitleService;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private int index;	//页面
	public void setIndex(int index) {
		this.index = index;
	}
	private int ac;
	public void setAc(int ac) {
		this.ac = ac;
	}
	
	public String newPublic(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user != null){
			return "newPublicSuccess";
		}
		return "Input";
	}
	
	public String newBlog(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user != null){
			//发布文章的用户UUID
			arcitle.setPublicuuid(user.getUuid());
			arcitle.setData(new Date().toString());
			//保存文章
			arcitleService.save(arcitle);
			return "addSuccess";
		}
		return "Input";
	}
	
	public String show(){
		//根据ID显示博文
		if(arcitle.getId() != null){
			Arcitle exarcitle = arcitleService.findById(arcitle.getId());
			User user = userService.findUserByUUID(exarcitle.getPublicuuid());
			Detail de = new Detail();
			de.setAclass(exarcitle.getAclass());
			de.setAuthor(exarcitle.getAuthor());
			de.setData(exarcitle.getData());
			de.setId(exarcitle.getId());
			de.setText(exarcitle.getNews());
			de.setTitle(exarcitle.getTitle());
			de.setUsername(user.getUsername());
			//保存在session中
			ServletActionContext.getRequest().getSession().setAttribute("detail", de);
			return "showSuccess";
		}
		return "showInpu";
	}
	
	private void ItPub(){
		ServletActionContext.getRequest().getSession().removeAttribute("listItArcitle");
		ServletActionContext.getRequest().getSession().removeAttribute("index");
		if(index < 0){
			index = 0;
		}
		List<Arcitle> listItArcitle = arcitleService.findByItLimit("IT类博文",index);
		if(listItArcitle.size() > 0){
			ServletActionContext.getRequest().getSession().setAttribute("listItArcitle", listItArcitle);
			ServletActionContext.getRequest().getSession().setAttribute("index", index);
			ServletActionContext.getRequest().getSession().setAttribute("ac", 1);
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("index", index-100);
			ServletActionContext.getRequest().getSession().setAttribute("ac", 1);
		}
	}
	
	private void OtherPub(){
		ServletActionContext.getRequest().getSession().removeAttribute("listItArcitle");
		ServletActionContext.getRequest().getSession().removeAttribute("index");
		if(index < 0){
			index = 0;
		}
		List<Arcitle> listItArcitle = arcitleService.findByItLimit("其它博文",index);
		if(listItArcitle.size() > 0){
			ServletActionContext.getRequest().getSession().setAttribute("listItArcitle", listItArcitle);
			ServletActionContext.getRequest().getSession().setAttribute("index", index);
			ServletActionContext.getRequest().getSession().setAttribute("ac", 2);
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("index", index-100);
			ServletActionContext.getRequest().getSession().setAttribute("ac", 2);
		}
	}
	//IT博文
	public String it(){
		ItPub();
		return "it";
	}
	
	//其他博文
	public String other(){
		OtherPub();
		return "other";
	}
	
	public String pageone(){
		if(ac == 1){
			ServletActionContext.getRequest().getSession().removeAttribute("ac");
			ItPub();
			return "it";
		}else{
			ServletActionContext.getRequest().getSession().removeAttribute("ac");
			OtherPub();
			return "other";
		}
	}
	//发布博文的名字
	private String name;
	public void setName(String name) {
		this.name = name;
	}
	//根据发布者显示博文
	public String peron(){
		if(arcitle.getPublicuuid() != null && name!=null){
			List<Arcitle> list = arcitleService.findByUUID(arcitle.getPublicuuid());
			ServletActionContext.getRequest().getSession().setAttribute("perlist", list);
			ServletActionContext.getRequest().getSession().setAttribute("name", name);
			return "peronsuccess";
		}
		return "Input";
	}
	
	//根据用户查询文章--管理文章
	public String showPerson(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user != null){
			List<Arcitle> list = arcitleService.findByUUID(user.getUuid());
			ServletActionContext.getRequest().getSession().setAttribute("manarticles", list);
			return "showPersonSuccess";
		}
		return "showInpu";
	}
	
	//根据Id删除文章
	public String delete(){
		arcitleService.deleteByid(arcitle);
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		List<Arcitle> list = arcitleService.findByUUID(user.getUuid());
		ServletActionContext.getRequest().getSession().setAttribute("manarticles", list);
		return "showPersonSuccess";
	}
}

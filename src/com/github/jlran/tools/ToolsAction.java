package com.github.jlran.tools;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.github.jlran.user.UserService;
import com.github.jlran.vo.Detail;
import com.github.jlran.vo.News;
import com.github.jlran.vo.Tools;
import com.github.jlran.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ToolsAction extends ActionSupport implements ModelDriven<Tools>{

	private Tools tools = new Tools();
	@Override
	public Tools getModel() {
		return tools;
	}
	
	private ToolsService toolsService;
	public void setToolsService(ToolsService toolsService) {
		this.toolsService = toolsService;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String addTool(){
		if(tools.getTitle() != null && tools.getText() != null){
			User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(user != null){
				tools.setPublicuuid(user.getUuid());
				tools.setData(new Date().toString());
				toolsService.save(tools);
				return "addToolsSuccess";
			}
		}
		return "Input";
	}
	
	public String show(){
		if(tools != null){
			Tools eatools = toolsService.findById(tools.getId());
			User user = userService.findUserByUUID(eatools.getPublicuuid());
			if(eatools != null){
				Detail de = new Detail();
				de.setAclass("工具");
				de.setAuthor(user.getUsername());
				de.setData(eatools.getData());
				de.setId(eatools.getId());
				de.setText(eatools.getText());
				de.setTitle(eatools.getTitle());
				de.setUsername(user.getUsername());
				ServletActionContext.getRequest().getSession().setAttribute("detail", de);
				return "showSuccess";
			}
		}
		return "showInpu";
	}
	
	public String showall(){
		List<Tools> list = toolsService.findAll();
		ServletActionContext.getRequest().getSession().setAttribute("listTools", list);
		return "showallSuccess";
	}
	
	//管理工具
	public String showPerson(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user == null){
			return "showInpu";
		}
		List<Tools> list = toolsService.findByUUID(user.getUuid());
		ServletActionContext.getRequest().getSession().setAttribute("showTools", list);
		return "showPersonSuccess";
	}
	
	//删除工具 根据id
	public String delete(){
		toolsService.delete(tools);
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		List<Tools> list = toolsService.findByUUID(user.getUuid());
		ServletActionContext.getRequest().getSession().setAttribute("showTools", list);
		return "showPersonSuccess";
	}
}

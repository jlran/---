package com.github.jlran.user;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.github.jlran.utils.UUIDUtils;
import com.github.jlran.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}	
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//验证码
	private String checkCode;
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
	//注册页面
	public String registPage(){
		return "registPageSuccess";
	}
	
	//登录页面
	public String loginPage(){
		return "loginPageSuccess";
	}
	
	//注册
    public String regist(){
    	//验证码
    	String check = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
    	if(!check.equalsIgnoreCase(checkCode)){
    		this.addActionError("验证码错误！");
    		return "registInput";
    	}
    	
    	//查询用户是否存在
    	User existuser = userService.findUserByQq(user.getQq());
    	if(existuser == null){
    		//保存用于
    		user.setPermiss(0);	//注册会员
    		String uuid = UUIDUtils.getUUID();
    		user.setUuid(uuid);
    		userService.Save(user);
    		return "registSuccess";
    	}
    	this.addActionError("该用户的QQ已经注册过了");    	
    	return "registInput";
    }
    
    //登录
    public String login(){
    	//验证码
    	String check = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
    	if(!check.equalsIgnoreCase(checkCode)){
    		this.addActionError("验证码错误！");
    		return "loginInput";
    	}
    	User existuser = userService.Login(user);
    	if(existuser != null){
    		//保存用户在session
    		ServletActionContext.getRequest().getSession().setAttribute("user", existuser);
    		return "loginSuccess";
    	}
    	this.addActionError("QQ或者密码错误！");
    	return "loginInput";
    }
    //注销
    public String quit(){
    	ServletActionContext.getRequest().getSession().invalidate();
    	return "quit";
    }
    
    //进入后台
    public String manager(){
    	User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
    	if(user != null){
    		//按照权限进入后台
    		if(user.getPermiss() == 0){
    			//普通用户
    			return "vip";
    		}else{
    			//管理员
    			return "admin";
    		}
    	}
    	return "quit";
    }
    
    //显示所有会员
    public String showall(){
    	List<User> list = userService.findUserAll();
    	ServletActionContext.getRequest().getSession().setAttribute("listuser", list);
    	return "showAll";
    }
    
    //修改用户密码
    private String oldpassword;
    public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
    public String updatepwd(){
    	User u = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
    	if(u.getPassword().equals(oldpassword)){
    		u.setPassword(user.getPassword());
    		userService.update(u);
    		ServletActionContext.getRequest().getSession().setAttribute("user", u);
    		return "updateSuccess";
    	}
    	return "updateInput";
    }
    
    //会员管理
    public String manvip(){
    	User u = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
    	if(u.getPermiss() != 0){	//0位会员
    		List<User> manvip = userService.findUserAll();
    		ServletActionContext.getRequest().getSession().setAttribute("manvip", manvip);
    		return "manvipSuccess";
    	}
    	return "quit";
    }
    
    //根据ID删除用户
    public String delete(){
    	if(user.getId() == null){
    		return "quit";
    	}
    	userService.delete(user);
    	List<User> manvip = userService.findUserAll();
		ServletActionContext.getRequest().getSession().setAttribute("manvip", manvip);
		return "manvipSuccess";
    }
    
}

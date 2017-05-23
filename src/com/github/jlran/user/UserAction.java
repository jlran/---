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
	
	//��֤��
	private String checkCode;
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
	//ע��ҳ��
	public String registPage(){
		return "registPageSuccess";
	}
	
	//��¼ҳ��
	public String loginPage(){
		return "loginPageSuccess";
	}
	
	//ע��
    public String regist(){
    	//��֤��
    	String check = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
    	if(!check.equalsIgnoreCase(checkCode)){
    		this.addActionError("��֤�����");
    		return "registInput";
    	}
    	
    	//��ѯ�û��Ƿ����
    	User existuser = userService.findUserByQq(user.getQq());
    	if(existuser == null){
    		//��������
    		user.setPermiss(0);	//ע���Ա
    		String uuid = UUIDUtils.getUUID();
    		user.setUuid(uuid);
    		userService.Save(user);
    		return "registSuccess";
    	}
    	this.addActionError("���û���QQ�Ѿ�ע�����");    	
    	return "registInput";
    }
    
    //��¼
    public String login(){
    	//��֤��
    	String check = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
    	if(!check.equalsIgnoreCase(checkCode)){
    		this.addActionError("��֤�����");
    		return "loginInput";
    	}
    	User existuser = userService.Login(user);
    	if(existuser != null){
    		//�����û���session
    		ServletActionContext.getRequest().getSession().setAttribute("user", existuser);
    		return "loginSuccess";
    	}
    	this.addActionError("QQ�����������");
    	return "loginInput";
    }
    //ע��
    public String quit(){
    	ServletActionContext.getRequest().getSession().invalidate();
    	return "quit";
    }
    
    //�����̨
    public String manager(){
    	User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
    	if(user != null){
    		//����Ȩ�޽����̨
    		if(user.getPermiss() == 0){
    			//��ͨ�û�
    			return "vip";
    		}else{
    			//����Ա
    			return "admin";
    		}
    	}
    	return "quit";
    }
    
    //��ʾ���л�Ա
    public String showall(){
    	List<User> list = userService.findUserAll();
    	ServletActionContext.getRequest().getSession().setAttribute("listuser", list);
    	return "showAll";
    }
    
    //�޸��û�����
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
    
    //��Ա����
    public String manvip(){
    	User u = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
    	if(u.getPermiss() != 0){	//0λ��Ա
    		List<User> manvip = userService.findUserAll();
    		ServletActionContext.getRequest().getSession().setAttribute("manvip", manvip);
    		return "manvipSuccess";
    	}
    	return "quit";
    }
    
    //����IDɾ���û�
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

package com.github.jlran.jianli;
import org.apache.struts2.ServletActionContext;

import com.github.jlran.vo.Jianli;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class JianliAction extends ActionSupport implements ModelDriven<Jianli>{
	private Jianli jianli = new Jianli();
	@Override
	public Jianli getModel() {
		return jianli;
	}
	
	private JianliService jianliService;
	public void setJianliService(JianliService jianliService) {
		this.jianliService = jianliService;
	}
	
	public String show(){
		Jianli jianli = jianliService.find();
		ServletActionContext.getRequest().getSession().setAttribute("jianli", jianli);
		return "showSuccess";
	}
	
	public String update(){
		jianli.setId(1);
		jianliService.save(jianli);
		return  "update";
	}
	
	public String updatejianli(){
		return "updatejianli";
	}
}

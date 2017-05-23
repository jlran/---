package com.github.jlran.wechart;

import java.awt.image.RescaleOp;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.github.jlran.utils.UUIDUtils;
import com.github.jlran.wechart.vo.Chartnews;
import com.github.jlran.wechart.vo.Charttable;
import com.github.jlran.wechart.vo.Wechartuser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ChartAction extends ActionSupport{
	private ChartService chartService;
	public void setChartService(ChartService chartService) {
		this.chartService = chartService;
	}
	
	private Wechartuser wechartuser = new Wechartuser(); //�û�
	public void setWechartuser(Wechartuser wechartuser) {
		this.wechartuser = wechartuser;
	}
	
	private String name;
	public void setName(String name) {
		this.name = name;
	}
	
	
	//����������
	public String creat(){
		String uuid= UUIDUtils.getUUID();
		String date = new Date().toString();
		wechartuser.setChartid(uuid);
		wechartuser.setName(name);
		Charttable charttable = new Charttable();
		charttable.setChartid(uuid);
		charttable.setDate(date);
		if(wechartuser.getName() == null){
			wechartuser.setName("΢֮��-�����û�");
		}
		try{
			//����������
			chartService.creatTable(charttable);
			//�����û���session
			ServletActionContext.getRequest().getSession().setAttribute("wechartuser", wechartuser);
			return "creatSuccess";
		}catch(Exception e){
			return "creatInput";
		}
	}
	
	//���յ�������
	private String sendcontext;
	public void setSendcontext(String sendcontext) {
		this.sendcontext = sendcontext;
	}
	
	//���յ����ݱ��浽���ݿ�
	public String send(){
		//��ȡ�û�
		Wechartuser user = (Wechartuser) ServletActionContext.getRequest().getSession().getAttribute("wechartuser");
		//��ȡ����
		Chartnews chartnews = new Chartnews();
		chartnews.setChartid(user.getChartid());
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = df.format(new Date());
		chartnews.setDate(date);
		chartnews.setName(user.getName());
		chartnews.setText(sendcontext);
		chartService.saveNews(chartnews);
		return "sendSuccess";
	}
	//�������ݵ�
	public String receive(){
		//��ȡ�û�
		Wechartuser user = (Wechartuser) ServletActionContext.getRequest().getSession().getAttribute("wechartuser");
		//�����ݿ��л�ȡ�����¼������20����
		List<Chartnews>  list =chartService.findNewsLimit(user.getChartid());
		String str = "";
		
		for (Chartnews chartnews : list) {
			str +="<font color='#191970'>"+ chartnews.getName() + "</font>---" + chartnews.getDate() +":-> " + chartnews.getText() +  "<br/>";
		}
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(str);
		} catch (Exception e) {
		}
		return NONE;
	}
	
	private String id;
	public void setId(String id) {
		this.id = id;
	}
	
	public String login(){
		
		if("".equals(name)){
			return "loginInput";
		}
		if("".equals(id)){
			return "loginInput";
		}
		wechartuser.setName(name);
		wechartuser.setChartid(id);
		ServletActionContext.getRequest().getSession().setAttribute("wechartuser", wechartuser);
		return "loginSuccess";

	}
	
	
}

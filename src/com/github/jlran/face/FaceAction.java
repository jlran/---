package com.github.jlran.face;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONUtil;
import org.hibernate.Hibernate;
import org.json.JSONArray;
import org.json.JSONObject;

import sun.misc.BASE64Decoder;

import com.baidu.aip.face.AipFace;
import com.github.jlran.user.UserService;
import com.github.jlran.vo.Face;
import com.github.jlran.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class FaceAction extends ActionSupport implements ModelDriven<Face>{
	
	 //设置APPID/AK/SK
    public static final String APP_ID = "9481815";
    public static final String API_KEY = "Xjhv2oR0SFnO9VQgZWgnrYjV";
    public static final String SECRET_KEY = "Vf5Yz8dypAwhilEtP8gLiRdF0mPOz3fE";
	
	private Face face = new Face();
	@Override
	public Face getModel() {
		return face;
	}
	
	private String imageDateB6411;
	public void setImageDateB6411(String imageDateB6411) {
		this.imageDateB6411 = imageDateB6411;
	}
	private String imageDateB6412;
	public void setImageDateB6412(String imageDateB6412) {
		this.imageDateB6412 = imageDateB6412;
	}
	private String imageDateB6413;
	public void setImageDateB6413(String imageDateB6413) {
		this.imageDateB6413 = imageDateB6413;
	}
	
	private String imageDateB6414;
	public void setImageDateB6414(String imageDateB6414) {
		this.imageDateB6414 = imageDateB6414;
	}
	
	private FaceService faceService;
	public void setFaceService(FaceService faceService) {
		this.faceService = faceService;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private String username;
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String loginPage(){
		return "loginPageSuccess";
	}
	
	public void upimagedata(){
	    face.setFace1(imageDateB6411);
	    face.setFace2(imageDateB6412);
	    face.setFace3(imageDateB6413);
	    face.setUuid(username);
	    //先根据ID查询是否有数据 有就更新 没有就保存
	    Face findByQQ = faceService.findByQQ(username);
	    if(findByQQ == null){
	    	faceService.save(face);
	    }else{
	    	face.setId(findByQQ.getId());
	    	faceService.update(face);
	    }
	}
	
	public void login(){
		
		
		if(imageDateB6414 != null){
			try {
				BASE64Decoder decoder = new BASE64Decoder();
				//查找数据库的文件
				Face face = faceService.findByQQ(username);
				if(face != null){
					//转码得到图片数据
					byte[] b1 = decoder.decodeBuffer(imageDateB6414);
					byte[] b2 = decoder.decodeBuffer(face.getFace1());
					byte[] b3 = decoder.decodeBuffer(face.getFace2());
					byte[] b4 = decoder.decodeBuffer(face.getFace3());
					
//					String path1 = "D:/image/img1.png";
//			        String path2 = "D:/image/img2.png";
//			        String path3 = "D:/image/img3.png";
//			        String path4 = "D:/image/img4.png";
					
					String path1 = "/home/bae/image/img1.png";
			        String path2 = "/home/bae/image/img2.png";
			        String path3 = "/home/bae/image/img3.png";
			        String path4 = "/home/bae/image/img4.png";
					
					File file1 = new File(path1);
					File file2 = new File(path2);
					File file3 = new File(path3);
					File file4 = new File(path4);
					
					FileOutputStream stream1 = new FileOutputStream(file1);
					FileOutputStream stream2 = new FileOutputStream(file2);
					FileOutputStream stream3 = new FileOutputStream(file3);
					FileOutputStream stream4 = new FileOutputStream(file4);
					
					stream1.write(b1);
					stream1.flush();
					stream1.close();
					stream2.write(b2);
					stream2.flush();
					stream2.close();
					stream3.write(b3);
					stream3.flush();
					stream3.close();
					stream4.write(b4);
					stream4.flush();
					stream4.close();
					
					//百度API比对
				    // 初始化一个FaceClient
			        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
	
			        // 可选：设置网络连接参数
		            client.setConnectionTimeoutInMillis(2000);
			        client.setSocketTimeoutInMillis(60000);
			        // 调用API
			        
			        ArrayList<String> pathArray = new ArrayList<String>();
			        pathArray.add(path1);
			        pathArray.add(path2);
			        pathArray.add(path3);
			        pathArray.add(path4);
			        JSONObject response = client.match(pathArray);
			        //System.out.println(response.toString());
			        JSONArray jsonArray = response.getJSONArray("results");
			        int score = 0;
			        for(int i =0; i < jsonArray.length(); i++){
			        	int sc = jsonArray.getJSONObject(i).getInt("score");
			        	score += sc;
			        }
			        //分数大于91分 就相当于该用户是本人
			        if(((score*1.0) / 3) > 91 ){
			        		User user= userService.findUserByQq(username);
			        		ServletActionContext.getRequest().getSession().setAttribute("user", user);
			        }else{
			        	this.addActionMessage("没有该用户或者QQ输入错误！");
			        }
				}else{
					this.addActionMessage("没有该用户或者QQ输入错误！");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.addActionMessage("没有拍照哦！");
		}
	}
}

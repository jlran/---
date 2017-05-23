package com.github.jlran.vo;

import java.sql.Blob;

public class Face {
	private int id;
	private String uuid;
	private String face1;
	private String face2;
	private String face3;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFace1() {
		return face1;
	}
	public void setFace1(String face1) {
		this.face1 = face1;
	}
	public String getFace2() {
		return face2;
	}
	public void setFace2(String face2) {
		this.face2 = face2;
	}
	public String getFace3() {
		return face3;
	}
	public void setFace3(String face3) {
		this.face3 = face3;
	}
}

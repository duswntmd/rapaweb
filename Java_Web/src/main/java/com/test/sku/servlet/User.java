package com.test.sku.servlet;

public class User {
	private String uid;
	private String pwd;
	
	User(){}
	
	User(String uid){
		this.uid = uid;
	}
	
	User(String uid, String pwd){
		this.uid = uid;
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		String str = String.format("%s\t%s", 
				this.getUid(),
				this.getPwd());
		return str;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}

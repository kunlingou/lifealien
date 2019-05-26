package com.kunlinr.lifealien.test.serializable;

import java.io.Serializable;

public class Person implements Serializable{
	private String userName;
	private String password;
	private String nickName;
	private String title;
	private String org;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String toString() {
        return "userName:"+userName+" password:"+password+" nickName:"+nickName+" title:"+title+" org"+org;
    }
}

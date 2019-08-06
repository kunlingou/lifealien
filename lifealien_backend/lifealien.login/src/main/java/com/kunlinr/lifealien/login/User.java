package com.kunlinr.lifealien.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	@Column
    private String userUuid;   //用户UUID
	@Column
    private String username;    //用户名
	@Column
    private String password;    //用户密码
	@Column
    private String email;       //用户邮箱
	@Column
    private String telephone;   //电话号码
	@Column
    private String role;        //用户角色
	@Column
    private String image;       //用户头像
	@Column
    private String lastIp;     //上次登录IP
	@Column
    private String lastTime;   //上次登录时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userUuid=" + userUuid + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", telephone=" + telephone + ", role=" + role + ", image=" + image + ", lastIp="
				+ lastIp + ", lastTime=" + lastTime + "]";
	}
}

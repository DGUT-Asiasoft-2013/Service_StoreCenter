package org.everyday2point5.fivestore.entity;

import java.io.Serializable;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;

import org.everyday2point5.fivestore.util.BaseEntity;
import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User extends BaseEntity implements Serializable{
	String account;
	String password;
	public String user_name;
	public String avatar;
	String email;
	float money;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	@Column(unique=true)
	public String getAccount() {
		return account;
	}
	@Column(nullable = false)
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public String getAvatar() {
		return avatar;
	}
	@Column(nullable = false)
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setAccount(String account2) {
		this.account = account2;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCurrentUser(HttpServletRequest request){
		return getAccount();

	}

}

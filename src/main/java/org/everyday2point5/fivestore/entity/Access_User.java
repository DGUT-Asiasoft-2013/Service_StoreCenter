package org.everyday2point5.fivestore.entity;

import javax.persistence.Entity;

import org.everyday2point5.fivestore.util.BaseEntity;
@Entity
public class Access_User extends BaseEntity{
	
	String user_name;
	Integer user_id;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
}

package org.everyday2point5.fivestore.entity;

import javax.persistence.Entity;

import org.everyday2point5.fivestore.util.BaseEntity;
@Entity
public class UserInformation extends BaseEntity {

	int user_id;
	String sex;
	String birth;
	String place;
	String whats_up;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getWhats_up() {
		return whats_up;
	}
	public void setWhats_up(String whats_up) {
		this.whats_up = whats_up;
	}
	
	
}

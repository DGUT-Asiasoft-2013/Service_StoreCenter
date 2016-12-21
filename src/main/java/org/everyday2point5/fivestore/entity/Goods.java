package org.everyday2point5.fivestore.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.everyday2point5.fivestore.util.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Goods  extends DateRecord implements Serializable{
	/* 商品名称，
	 * 商品编号
	 * 图片*/
	String title;
	User user;
	int 
	goods_count; 
	String goods_img, text;
	

	float price;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	
	public int getGoods_count() {
		return goods_count;
	}
	public void setGoods_count(int goods_count) {
		this.goods_count = goods_count;
	}

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

	
	@Transient
	public String getGoods_img() {
		return goods_img;
	}
	public void setGoods_img(String goods_img) {
		this.goods_img = goods_img;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Column(nullable = false)
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
}

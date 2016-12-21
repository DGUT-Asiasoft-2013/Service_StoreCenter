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
public class Goods extends DateRecord implements Serializable {
	/*
	 * 商品名称， 商品编号 图片
	 */
	String title;
	int user_id;
	int goods_count;
	User user;
	String goods_img;
	String text;

	Date createDate;
	Date editDate;
	float price;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
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


	@Column(unique = true)
	@Transient
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

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

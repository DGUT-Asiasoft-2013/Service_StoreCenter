package org.everyday2point5.fivestore.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.everyday2point5.fivestore.util.BaseEntity;

@Entity
public class GoodsList extends BaseEntity{

	String goods_list_name;
	String goods_list_text;
	String goods_list_image;
	String goods_list_item;
	Date createTime;
	int seller_id;
	String seller_name;

	
	
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public String getSeller_name() {
		return seller_name;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getGoods_list_name() {
		return goods_list_name;
	}
	public void setGoods_list_name(String goods_list_name) {
		this.goods_list_name = goods_list_name;
	}
	public String getGoods_list_text() {
		return goods_list_text;
	}
	public void setGoods_list_text(String goods_list_text) {
		this.goods_list_text = goods_list_text;
	}
	public String getGoods_list_image() {
		return goods_list_image;
	}
	public void setGoods_list_image(String goods_list_image) {
		this.goods_list_image = goods_list_image;
	}
	public String getGoods_list_item() {
		return goods_list_item;
	}
	public void setGoods_list_item(String goods_list_item) {
		this.goods_list_item = goods_list_item;
	}

	
}

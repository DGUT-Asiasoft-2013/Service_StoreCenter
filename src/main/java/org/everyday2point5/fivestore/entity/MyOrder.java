package org.everyday2point5.fivestore.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.everyday2point5.fivestore.util.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class MyOrder  extends BaseEntity implements Serializable{
	int
	status,
	amount;
	String order_num;
	String goods_id;
	
	String name, phone, address;
	Date createDate, editDate;
	Goods goods;
	int buyer_id;
	Integer sale_id;
	@Column
	public Integer getSale_id() {
		return sale_id;
	}

	public void setSale_id(Integer integer) {
		this.sale_id = integer;
	}

	@Column
	public int getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
	}

	@Column
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Column
	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id2) {
		this.goods_id = goods_id2;
	}

	
	@Column(updatable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column
	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	@PreUpdate
	void onPreUpdate(){
		editDate = new Date();
	}
	


	@PrePersist
	void onPrePresist(){
		createDate = new Date();
		editDate = new Date();
	}
	
	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(nullable = true)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(nullable = true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Column(unique = true)
	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num2) {
		this.order_num = order_num2;
	}



}


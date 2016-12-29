package org.everyday2point5.fivestore.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.everyday2point5.fivestore.util.BaseEntity;
import org.junit.Ignore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Comment  extends BaseEntity{
	/*id
	 *商品id
	 *订单id
	 *内容
	 *创建时间 */

	User author;	
	Goods goods;

	String text;
	Date createDate, editDate;
	String order_num;
	
	@Column
	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	
	@Column
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
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
	
	@ManyToOne
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	

	@ManyToOne(optional = false)
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
}

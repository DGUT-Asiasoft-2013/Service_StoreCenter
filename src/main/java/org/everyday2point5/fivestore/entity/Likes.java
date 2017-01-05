package org.everyday2point5.fivestore.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Likes {
	@Embeddable
	public static class Key implements Serializable{
	
		User user;
		Goods goods;
		
		
		@ManyToOne(optional = false)
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		@ManyToOne(optional = false)
		public Goods getGoods() {
			return goods;
		}
		public void setGoods(Goods goods) {
			this.goods = goods;
		}
		
		public boolean equals(Object obj){
			if(obj instanceof Key){
				Key other = (Key)obj;
				
				return goods.getId() == other.goods.getId() && user.getId() == other.user.getId();
			}
			else{
				return false;
			}
			
			
		}
		
		@Override
		public int hashCode() {
			return goods.getId();
		}
		
	}
	

	
	Key id;
	Date createDate;


	@EmbeddedId
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	
	@Column(updatable = false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@PrePersist
	void onPrePersist(){
		createDate = new Date();
	}
	
	
	
}



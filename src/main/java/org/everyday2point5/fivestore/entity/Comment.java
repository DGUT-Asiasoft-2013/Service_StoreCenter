package org.everyday2point5.fivestore.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.everyday2point5.fivestore.util.BaseEntity;


@Entity
public class Comment  extends BaseEntity  implements Serializable{
	/*id
	 *商品id
	 *订单id
	 *内容
	 *创建时间 */
	int 
	order_id;
	User author;
	
	Goods goods;
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	@ManyToOne(optional = false)
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
}

package org.everyday2point5.fivestore.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import org.everyday2point5.fivestore.util.BaseEntity;
import org.springframework.stereotype.Component;
@Component
@Entity
public class Inbox extends BaseEntity implements Serializable{
	
	/*私信内容
	 * 发送者id
	 * 接收者id
	 * 是否已读
	 * 时间
	 */
	
	
	String content;
	int send_id;
	int rec_id;
	boolean isread;
	Date createDate;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSend_id() {
		return send_id;
	}
	public void setSend_id(int send_id) {
		this.send_id = send_id;
	}
	public int getRec_id() {
		return rec_id;
	}
	public void setRec_id(int rec_id) {
		this.rec_id = rec_id;
	}
	public boolean isIsread() {
		return isread;
	}
	public void setIsread(boolean isread) {
		this.isread = isread;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}

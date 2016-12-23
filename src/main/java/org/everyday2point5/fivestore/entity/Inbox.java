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
	String send_name;
	String rec_name;
	boolean isread;
	Date createDate;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	public String getRec_name() {
		return rec_name;
	}
	public void setRec_name(String rec_name) {
		this.rec_name = rec_name;
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

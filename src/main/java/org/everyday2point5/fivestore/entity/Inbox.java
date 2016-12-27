package org.everyday2point5.fivestore.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;

import org.everyday2point5.fivestore.util.BaseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Inbox extends BaseEntity implements Serializable{
	
	/*私信内容
	 * 发送者id
	 * 接收者id
	 * 是否已读
	 * 时间
	 */
	
	
	String inboxContent;
	User send_user;
	User rec_user;
	boolean isread;
	Date createDate;
	String sign;
	
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getInboxContent() {
		return inboxContent;
	}
	public void setInboxContent(String content) {
		this.inboxContent = content;
	}

	
	
	public User getSend_user() {
		return send_user;
	}

	public void setSend_user(User send_user) {
		this.send_user = send_user;
	}
	public User getRec_user() {
		return rec_user;
	}

	public void setRec_user(User rec_user) {
		this.rec_user = rec_user;
	}
	
	
	
	public boolean isIsread() {
		return isread;
	}
	public void setIsread(boolean isread) {
		this.isread = isread;
	}
	
	@Column(updatable=false)
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

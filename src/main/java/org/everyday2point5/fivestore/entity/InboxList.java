package org.everyday2point5.fivestore.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import org.everyday2point5.fivestore.util.BaseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
@Entity
public class InboxList extends BaseEntity implements Serializable{

	
	
	Inbox last_inbox;
	String sign;
	String send_name;
	String rec_name;
	Date createDate;
	
	
	public Inbox getLast_inbox() {
		return last_inbox;
	}
	public void setLast_inbox(Inbox last_inbox) {
		this.last_inbox = last_inbox;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}

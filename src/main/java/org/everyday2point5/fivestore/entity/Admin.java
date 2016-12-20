package org.everyday2point5.fivestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.everyday2point5.fivestore.util.BaseEntity;

@Entity
public class Admin extends BaseEntity {
	String account;
	String passwordHash;
	
	@Column(unique=true)
	public String getAccount() {
		return account;
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
}

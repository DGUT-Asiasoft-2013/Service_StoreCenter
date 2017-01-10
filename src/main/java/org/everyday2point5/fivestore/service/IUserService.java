package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.User;

public interface IUserService {
	
	User create(String account, String passwordHash);

	void login(String account, String passwordHash);

	User changePassword(User user);
	
	User changeName(User user);
	
	User changeEmail(User user);

	void logout();
	User save(User user);
	User findByAccount(String account);

	User findOne(Integer uid);

	User findByEmail(String email);

	User findByName(String name);

	User findUserByName(String name);

	User findUserByAccount(String account);

}

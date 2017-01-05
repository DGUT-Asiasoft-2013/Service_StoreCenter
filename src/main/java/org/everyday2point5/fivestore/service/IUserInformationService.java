package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.UserInformation;

public interface IUserInformationService {

	public UserInformation getInformation(int user_id) ;

	public UserInformation save(UserInformation userInf);

}

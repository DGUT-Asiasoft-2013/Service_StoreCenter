package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.UserInformation;
import org.everyday2point5.fivestore.repository.IUserInformationRepository;
import org.everyday2point5.fivestore.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
@Transactional
public class DefaultUserInformationService implements IUserInformationService {

	@Autowired
	IUserInformationRepository userInformationRepo;
	
	@Override
	public UserInformation getInformation(int user_id) {
		// TODO Auto-generated method stub
		return userInformationRepo.findById(user_id);
	}

	@Override
	public UserInformation save(UserInformation userInf) {
		// TODO Auto-generated method stub
		return userInformationRepo.save(userInf);
	}

}

package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.User;
import org.everyday2point5.fivestore.entity.UserInformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUserInformationRepository extends PagingAndSortingRepository<UserInformation, Integer>{

	
	@Query("from UserInformation u where u.user_id = ?1")
	UserInformation findById(int user_id) ;

}

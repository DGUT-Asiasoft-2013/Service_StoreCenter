package org.everyday2point5.fivestore.repository;

import org.springframework.stereotype.Repository;
import org.everyday2point5.fivestore.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<User, Integer>{
	@Query("from User user where user.account = ?1")
	User findByAccount(String account);

	@Query("from User user where user.id = ?1")
	User findById(Integer uid);

	@Query("from User user where user.email = ?1")
	User findByEmail(String email);

	@Query("from User user where user.account = ?1")
	User findByName(String account);




}

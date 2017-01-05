package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.Downs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDownsRepository  extends PagingAndSortingRepository<Downs, Downs.Key>{

	@Query("select count(*) from Downs downs where downs.id.goods.id = ?1 ")
	public Integer downCount(int id);
	
	@Query("select count(*) from Downs downs where downs.id.user.id = ?1 and downs.id.goods.id= ?2")
	public int checkDownsExit(Integer id, Integer id2);



}

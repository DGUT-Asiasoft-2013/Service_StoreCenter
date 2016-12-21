package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGoodsRepository  extends PagingAndSortingRepository<Goods, Integer>{
	
	@Query("from Goods goods where goods.user.id = ?1")
	Page<Goods> findAllGoods(int id,Pageable request);

	@Query("from Goods g where g.title like %?1%")
	Page<Goods> search(String text, Pageable request);



}

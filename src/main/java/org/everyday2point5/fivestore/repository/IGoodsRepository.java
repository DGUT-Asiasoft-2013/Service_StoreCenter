package org.everyday2point5.fivestore.repository;

import java.util.List;

import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.User;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGoodsRepository  extends PagingAndSortingRepository<Goods, Integer>{
	
	@Query("from Goods goods where goods.user.id = ?1 and goods.status <> 2 ")
	Page<Goods> findAllGoods(int id,Pageable request);

	@Query("from Goods g where g.title like %?1% and g.status <> 2 ")
	Page<Goods> search(String text, Pageable request);
	
	@Query("from Goods g where g.id = ?1 ")
	Goods findOneGoods(Integer id);

	@Query("from Goods g where g.id = ?1")
	User findOneSaler(Integer id);

	@Query("from Goods g where g.sort = ?1 and g.status <> 2 ")
	Page<Goods> findSortList(String sortType, Pageable request);

	@Query("from Goods g where g.id in ?1")
	List<Goods> findGoodsInList(int[] id, Pageable request);



}

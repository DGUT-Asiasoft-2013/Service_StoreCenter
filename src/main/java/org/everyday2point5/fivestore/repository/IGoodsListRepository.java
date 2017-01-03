package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.GoodsList;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGoodsListRepository extends PagingAndSortingRepository<GoodsList, Integer> {

	@Query("from GoodsList goodsList where goodsList.seller_name = ?1")
	Page<GoodsList> findSellerGoodsList(String user_name, Pageable request);

}

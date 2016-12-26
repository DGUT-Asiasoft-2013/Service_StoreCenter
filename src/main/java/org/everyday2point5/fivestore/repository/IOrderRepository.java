package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.MyOrder;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface IOrderRepository  extends PagingAndSortingRepository<MyOrder, Integer>{
	//chaxun
	@Query("from MyOrder myorder where myorder.order_num= ?1")
	MyOrder findOneOrder(String order_id);

	@Query("from  MyOrder myorder where myorder.status = 1")
	Page<MyOrder> findAllOrders(Pageable request);
	
	@Query("from  MyOrder myorder where myorder.status = 1 or myorder.status = 2 or myorder.status = 3 or myorder.status = 0")
	Page<MyOrder> findAllDeals(Pageable request);

	@Query("select order_num from  MyOrder myorder where myorder.goods_id = ?1")
	String findOrderNum(String goods_id);

	

	
}

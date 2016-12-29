package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.MyOrder;
import org.everyday2point5.fivestore.entity.User;
import org.springframework.data.domain.Page;

public interface IOrderService {

	MyOrder save(MyOrder order);


	MyOrder findOneOrder(String order_id);

	User findOne(Integer uid);

	Page<MyOrder> findAllMyDeal(Integer uid, int page);


	String findOrderNum(Integer id);


	Page<MyOrder> findAll(int page, Integer sale_id);




	
}


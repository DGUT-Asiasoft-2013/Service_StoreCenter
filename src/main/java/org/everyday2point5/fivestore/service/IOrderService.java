package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.MyOrder;
import org.everyday2point5.fivestore.entity.User;
import org.springframework.data.domain.Page;

public interface IOrderService {

	MyOrder save(MyOrder order);


	MyOrder findOneOrder(String order_id);

	User findOne(Integer uid);


	Page<MyOrder> findAll(int page);


	Page<MyOrder> findAllMyDeal(int page);


	String findOrderNum(String goods_id);

	
}


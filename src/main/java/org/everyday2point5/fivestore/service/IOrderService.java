package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.MyOrder;
import org.everyday2point5.fivestore.entity.User;
import org.springframework.data.domain.Page;

public interface IOrderService {

	MyOrder save(MyOrder order);

	Page<MyOrder> findAll(int page,Integer user_id);


	MyOrder findOneOrder(String order_id);

	User findOne(Integer uid);

}


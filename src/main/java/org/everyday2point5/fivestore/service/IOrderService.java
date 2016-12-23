package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.MyOrder;
import org.springframework.data.domain.Page;

public interface IOrderService {

	MyOrder save(MyOrder order);

	Page<MyOrder> findAll(int page);




	MyOrder findOneOrder(String order_id);

}

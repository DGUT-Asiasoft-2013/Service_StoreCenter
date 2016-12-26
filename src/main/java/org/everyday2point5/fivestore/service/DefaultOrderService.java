package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.MyOrder;
import org.everyday2point5.fivestore.entity.User;
import org.everyday2point5.fivestore.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
public class DefaultOrderService implements IOrderService {
	@Autowired 
	IOrderRepository  repo;
	@Override
	public MyOrder save(MyOrder order) {
		return repo.save(order);
	}
/*	@Override
	public Page<MyOrder> findAll(int page,Integer user_id, String goods_id) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(page, 10, sort);
		return repo.findAllOrders(request,user_id, goods_id);
	}*/
	@Override
	public MyOrder findOneOrder(String order_id) {
		return repo.findOneOrder(order_id);
	}
	@Override
	public User findOne(Integer uid) {
		// TODO Auto-generated method stub
		return findOne(uid);
	}
	@Override
	public Page<MyOrder> findAll(int page) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(page, 10, sort);
		return  repo.findAllOrders(request);
	}


	

}


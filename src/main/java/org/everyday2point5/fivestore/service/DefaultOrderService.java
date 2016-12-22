package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.MyOrder;
import org.everyday2point5.fivestore.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
@Transactional
public class DefaultOrderService implements IOrderService {
	@Autowired 
	IOrderRepository  repo;
	@Override
	public MyOrder save(MyOrder order) {
		return repo.save(order);
	}

}

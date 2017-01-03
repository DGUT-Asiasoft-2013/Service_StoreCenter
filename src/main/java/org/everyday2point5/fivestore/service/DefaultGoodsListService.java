package org.everyday2point5.fivestore.service;


import org.everyday2point5.fivestore.entity.GoodsList;
import org.everyday2point5.fivestore.repository.IGoodsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
@Transactional
public class DefaultGoodsListService implements IGoodsListService{
	
	@Autowired
	IGoodsListRepository goodsListRepo;
	
	@Override
	public GoodsList save(GoodsList goods) {
		return goodsListRepo.save(goods);
	}

}


package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.repository.IGoodsRepository;
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
@Transactional
public class DefaultGoodsService implements IGoodsService {
	@Autowired
	IGoodsRepository goodsRepo;
	
	@Override
	public Goods save(Goods goods) {
		return goodsRepo.save(goods);
	}

	@Override
	public Page<Goods> findAllGoods(int i) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(i, 10, sort);
		return goodsRepo.findAllGoods(request);
	}

}


package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.User;
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
	public Page<Goods> findAllGoods(int id, int page) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(page, 10, sort);
		return goodsRepo.findAllGoods(id, request);
	}

	@Override
	public Page<Goods> searchText(String text, int page) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(page, 10, sort);
		return goodsRepo.search(text, request);
	}

	public void delete(Goods goods) {
		// TODO Auto-generated method stub
		goodsRepo.delete(goods);
	}

	@Override
	public Goods findOne(int id) {
		return goodsRepo.findOne(id);
	}
	@Override
	public Page<Goods> getFeeds(int page) {
		Sort sort=new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest=new PageRequest(page, 10,sort);
		return goodsRepo.findAll(pageRequest);
	}

	@Override
	public User findOneSaler(Integer id) {
		// TODO Auto-generated method stub
		return goodsRepo.findOneSaler(id);
	}

	@Override
	public Page<Goods>  sortList(String sortType, int page) {
		//分類查找
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(page, 10, sort);
		return goodsRepo.findSortList(sortType, request);
	}

}

package org.everyday2point5.fivestore.service;

import java.util.List;

import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.GoodsList;
import org.springframework.data.domain.Page;

public interface IGoodsListService {

	GoodsList save(GoodsList goodsList);

	Page<GoodsList> findSellerGoodsList(String user_name, int page);

	GoodsList findGoodsListById(int id);

	List<Goods> findGoodsInList(int[] id, int page);

	Page<GoodsList> findAllGoodsList(int page);
	
	
}

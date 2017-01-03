package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.GoodsList;
import org.springframework.data.domain.Page;

public interface IGoodsListService {

	GoodsList save(GoodsList goodsList);

	Page<GoodsList> findSellerGoodsList(String user_name, int page);
	
	
}

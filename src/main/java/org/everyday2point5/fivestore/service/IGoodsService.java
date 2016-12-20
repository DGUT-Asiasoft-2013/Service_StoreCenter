package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Goods;
import org.springframework.data.domain.Page;

public interface IGoodsService {

	Goods save(Goods goods);

	Page<Goods> findAllGoods(int id, int i);

	Page<Goods> searchText(String text, int page);

}

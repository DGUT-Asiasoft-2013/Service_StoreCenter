package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.MyOrder;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IOrderRepository  extends PagingAndSortingRepository<MyOrder, Integer>{

}

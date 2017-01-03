package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.GoodsList;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGoodsListRepository extends PagingAndSortingRepository<GoodsList, Integer> {

}

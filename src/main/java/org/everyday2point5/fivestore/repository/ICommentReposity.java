package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICommentReposity extends PagingAndSortingRepository<Goods, Integer>{
	@Query("from Comment c where c.goods.id = ?1")
	Page<Comment> findCommentsById(int goods_id, Pageable pageable);

}

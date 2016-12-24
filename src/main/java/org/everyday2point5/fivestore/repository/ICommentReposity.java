package org.everyday2point5.fivestore.repository;


import org.everyday2point5.fivestore.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ICommentReposity extends PagingAndSortingRepository<Comment, Integer>{
	
	@Query("from Comment comment where comment.goods_id=?1")
	Page<Comment> findAllOfGoodsId(String goodsId,Pageable page);

	@Query("select count(*) from Comment comment where comment.goods_id = ?1")
	 	int commentCountOfArticle(int goodsId);

}

package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.Likes;
import org.everyday2point5.fivestore.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikesRepository extends PagingAndSortingRepository<Likes, Likes.Key>{


	@Query("select count(*) from Likes likes where likes.id.goods.id = ?1 ")
	Integer likeCount(int id);
	

	@Query("select count(*) from Likes likes where likes.id.user.id = ?1 and likes.id.goods.id= ?2")
	int checkLikesExit( int user_id,int goods_id);

}

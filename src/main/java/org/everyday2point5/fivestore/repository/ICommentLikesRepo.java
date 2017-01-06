package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.CommentLikes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentLikesRepo extends PagingAndSortingRepository<CommentLikes, CommentLikes.Key>{

	@Query("select count(*) from CommentLikes likes where likes.id.comment.id = ?1 ")
	int comment_likeCount(int id);
	
	@Query("select count(*) from CommentLikes likes where likes.id.user.id = ?1 and likes.id.comment.id= ?2")
	int checkCommentLikesExit( Integer user_id,Integer id);
}

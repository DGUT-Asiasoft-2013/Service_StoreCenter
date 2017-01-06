package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.CommentDowns;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ICommentDownsRepository  extends PagingAndSortingRepository<CommentDowns, CommentDowns.Key>{
	@Query("select count(*) from CommentDowns downs where downs.id.comment.id = ?1 ")
	public Integer commentDownCount(int id);
	
	@Query("select count(*) from CommentDowns downs where downs.id.user.id = ?1 and downs.id.comment.id= ?2")
	public int checkCommentDownsExit(Integer id, Integer id2);

}

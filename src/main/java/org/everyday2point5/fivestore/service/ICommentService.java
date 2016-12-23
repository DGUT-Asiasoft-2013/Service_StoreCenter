package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Comment;

import org.springframework.data.domain.Page;

public interface ICommentService {
	
	Page<Comment> findCommentsOfArticle(int goods,int page);
	Comment save(Comment comment);
	
	int getCommentCountOfArticle(int goodsId);

}

package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

public interface ICommentService {
	Page<Comment> findCommentsOfGoods(int goods,int page);
	Comment save(Comment comment);
	
	int getCommentCountOfArticle(int goodsId);

}

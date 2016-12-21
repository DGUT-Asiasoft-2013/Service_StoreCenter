package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

public interface ICommentService {
@Autowired
	Page<Comment> getComments(int goods_id,int page);

}

package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.repository.ICommentReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Component
@Service
@Transactional
public class DefaultCommentService implements ICommentService{
	@Autowired
	ICommentReposity repo;
	
	@Override
	public Page<Comment> getComments(int goods_id,int page) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(page, 10, sort);
		return repo.findCommentsById(goods_id, request);
	}

}

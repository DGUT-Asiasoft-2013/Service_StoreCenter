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
	ICommentReposity commentRepo;

	@Override
	public Page<Comment> findCommentsOfGoods(int id, int page) {
		Sort sort=new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest=new PageRequest(page,5,sort);
		return commentRepo.findAllOfGoodsId(id,pageRequest);
	}

	@Override
	public Comment save(Comment comment) {
		
		return commentRepo.save(comment);
	}

	@Override
	public int getCommentCountOfArticle(int goodsId) {
		// TODO Auto-generated method stub
		return commentRepo.commentCountOfArticle(goodsId);
	}


}

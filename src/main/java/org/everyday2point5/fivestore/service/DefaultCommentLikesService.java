package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.CommentLikes;
import org.everyday2point5.fivestore.entity.CommentLikes.Key;
import org.everyday2point5.fivestore.entity.User;
import org.everyday2point5.fivestore.repository.ICommentLikesRepo;
import org.everyday2point5.fivestore.repository.ILikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Configuration
@Component
@Service
@Transactional
public class DefaultCommentLikesService implements ICommentLikesService{
	@Autowired
	ICommentLikesRepo likeRepo;
	
	@Override
	public void addCommentLike(User user, Comment comment) {
		CommentLikes.Key l = new Key();
		 l.setComment(comment);
		 l.setUser(user);
		
		 CommentLikes lk = new CommentLikes();
		 lk.setId(l);
		 likeRepo.save(lk);
	}

	@Override
	public void removeCommentLike(User user, Comment comment) {
		CommentLikes.Key key = new Key();
		
		key.setUser(user);
		key.setComment(comment);
		
		likeRepo.delete(key);
	}

	@Override
	public int commentLikeCount(int id) {
		// TODO Auto-generated method stub
		return likeRepo.comment_likeCount(id);
	}

	@Override
	public boolean checkCommentLikesEixt(Integer user, Integer comment_id) {
		// TODO Auto-generated method stub
		return likeRepo.checkCommentLikesExit(user, comment_id)>0;
	}

}

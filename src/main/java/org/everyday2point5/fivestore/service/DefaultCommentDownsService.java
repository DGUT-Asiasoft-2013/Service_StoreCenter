package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.CommentDowns;
import org.everyday2point5.fivestore.entity.CommentDowns.Key;
import org.everyday2point5.fivestore.entity.User;
import org.everyday2point5.fivestore.repository.ICommentDownsRepository;
import org.everyday2point5.fivestore.repository.IDownsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Configuration
@Component
@Service
@Transactional
public class DefaultCommentDownsService implements ICommentDownsService{

	@Autowired
	ICommentDownsRepository downsRepo;
	
	@Override
	public void addCommentDown(User user, Comment comment) {
		CommentDowns.Key l = new Key();
		 l.setComment(comment);
		 l.setUser(user);
		
		 CommentDowns lk = new CommentDowns();
		 lk.setId(l);
		 downsRepo.save(lk);
	}

	@Override
	public void removeCommentDown(User user, Comment comment) {
		CommentDowns.Key key = new Key();
		
		key.setUser(user);
		key.setComment(comment);
		
		downsRepo.delete(key);
	}

	@Override
	public Integer commentDownsCount(int id) {
		// TODO Auto-generated method stub
		return downsRepo.commentDownCount(id);
	}

	@Override
	public boolean checkCommentDownsExit(Integer id, Integer id2) {
		// TODO Auto-generated method stub
		return downsRepo.checkCommentDownsExit(id, id2)>0;
	}

}

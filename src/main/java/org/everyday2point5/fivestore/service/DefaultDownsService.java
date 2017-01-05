package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.Downs;
import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.User;
import org.everyday2point5.fivestore.repository.IDownsRepository;
import org.everyday2point5.fivestore.entity.Downs.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Configuration
@Component
@Service
@Transactional
public class DefaultDownsService implements IDownsService {

	@Autowired
	IDownsRepository downsRepo;
	
	
	@Override
	public void addDown(User user, Goods goods) {
		Downs.Key l = new Key();
		 l.setGoods(goods);
		 l.setUser(user);
		
		 Downs lk = new Downs();
		 lk.setId(l);
		 downsRepo.save(lk);
	}

	@Override
	public void removeDown(User user, Goods goods) {
		Downs.Key key = new Key();
		
		key.setUser(user);
		key.setGoods(goods);
		
		downsRepo.delete(key);
		
		
	}

	@Override
	public Integer downsCount(int id) {
		return downsRepo.downCount(id);
	}

	@Override
	public boolean checkDownsExit(Integer id, Integer id2) {
		// TODO Auto-generated method stub
		return downsRepo.checkDownsExit(id, id2)>0;
	}

	@Override
	public void addCommentDown(User user, Comment comment) {
		Downs.Key l = new Key();
		 l.setComment(comment);
		 l.setUser(user);
		
		 Downs lk = new Downs();
		 lk.setId(l);
		 downsRepo.save(lk);
	}

	@Override
	public void removeCommentDown(User user, Comment comment) {
		Downs.Key key = new Key();
		
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

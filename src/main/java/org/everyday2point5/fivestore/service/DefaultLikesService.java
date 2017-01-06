package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.Likes;
import org.everyday2point5.fivestore.entity.Likes.Key;
import org.everyday2point5.fivestore.entity.User;
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
public class DefaultLikesService implements ILikesService{
	@Autowired
	ILikesRepository likeRepo;
	@Override
	public void addLike(User user, Goods goods) {
			 Likes.Key l = new Key();
			 l.setGoods(goods);
			 l.setUser(user);
			
			 Likes lk = new Likes();
			 lk.setId(l);
			 likeRepo.save(lk);
		
	}

	@Override
	public void removeLike(User user, Goods goods) {
		
		Likes.Key key = new Key();
		
		key.setUser(user);
		key.setGoods(goods);
		
		likeRepo.delete(key);
		
	}

	@Override
	public Integer likeCount(int id) {
		
		return likeRepo.likeCount(id);
	}

	public boolean checkLikesExit(int user,int goods){
		return  likeRepo.checkLikesExit(user, goods)>0;
	}

	
}

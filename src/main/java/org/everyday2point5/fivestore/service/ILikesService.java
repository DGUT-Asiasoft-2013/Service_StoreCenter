package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.User;

public interface ILikesService  {

	void addLike(User user,Goods goods);

	void removeLike(User user, Goods goods);

	Integer likeCount(int id);

	boolean checkLikesExit(int user,int goods);

	
	

}

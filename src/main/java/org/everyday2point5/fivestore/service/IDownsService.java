package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.User;

public interface IDownsService {

	void addDown(User user, Goods goods);

	void removeDown(User user, Goods goods);

	Integer downsCount(int id);

	boolean checkDownsExit(Integer id, Integer id2);

	void addCommentDown(User user, Comment comment);

	void removeCommentDown(User user, Comment comment);

	Integer commentDownsCount(int id);

	boolean checkCommentDownsExit(Integer id, Integer id2);

	
	
}

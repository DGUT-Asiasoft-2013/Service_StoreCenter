package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.User;

public interface ICommentDownsService {

	void addCommentDown(User user, Comment comment);

	void removeCommentDown(User user, Comment comment);

	Integer commentDownsCount(int id);

	boolean checkCommentDownsExit(Integer id, Integer id2);

}

package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.User;

public interface ICommentLikesService {
	void addCommentLike(User user, Comment comment);

	void removeCommentLike(User user, Comment comment);

	int commentLikeCount(int id);

	boolean checkCommentLikesEixt(Integer id, Integer comment_id);

}

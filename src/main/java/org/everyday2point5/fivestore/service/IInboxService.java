package org.everyday2point5.fivestore.service;


import org.everyday2point5.fivestore.entity.Inbox;
import org.everyday2point5.fivestore.entity.InboxList;
import org.springframework.data.domain.Page;

public interface IInboxService {

	Inbox save(Inbox inbox);
	
	InboxList save(InboxList inboxList);

//	Page<Inbox> findAllInbox(String name, int i);
	
	Page<Inbox> inboxGetChat(String text, int page);

	Page<InboxList> findInboxList(String text, int page);

	InboxList findBySign(String sign);
}

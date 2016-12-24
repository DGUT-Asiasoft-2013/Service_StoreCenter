package org.everyday2point5.fivestore.service;


import org.everyday2point5.fivestore.entity.Inbox;
import org.springframework.data.domain.Page;

public interface IInboxService {

	Inbox save(Inbox inbox);

	Page<Inbox> findAllInbox(String name, int i);
	
	Page<Inbox> searchInbox(String text, int page);
	
}

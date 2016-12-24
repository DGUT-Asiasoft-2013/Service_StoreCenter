package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Inbox;
import org.everyday2point5.fivestore.repository.IInboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Component
@Service
@Transactional
public class DefaultInboxService implements IInboxService{
	
	@Autowired
	IInboxRepository inboxRepo;
	
	
	@Override
	public Inbox save(Inbox inbox){
		return inboxRepo.save(inbox);
	}
	
	@Override
	public Page<Inbox> findAllInbox(String name, int i) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(i, 10, sort);
		return inboxRepo.findAllInbox(name, request);
	}
	

	@Override
	public Page<Inbox> searchInbox(String text, int page) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(page, 10, sort);
		return inboxRepo.searchInbox(text, request);
	}
}

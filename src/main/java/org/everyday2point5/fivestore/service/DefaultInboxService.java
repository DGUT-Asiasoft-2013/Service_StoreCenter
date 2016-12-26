package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Inbox;
import org.everyday2point5.fivestore.entity.InboxList;
import org.everyday2point5.fivestore.repository.IInboxListRepository;
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
	
	@Autowired
	IInboxListRepository inboxListRepo;
	
	
	@Override
	public Inbox save(Inbox inbox){
		return inboxRepo.save(inbox);
	}
	
	@Override
	public InboxList save(InboxList inboxList){
		return inboxListRepo.save(inboxList);
	}
	
	@Override
	public InboxList findBySign(String sign){
		return inboxListRepo.findInboxListBySign(sign);
	}
	
//	@Override
//	public Page<Inbox> findAllInbox(String name, int i) {
//		Sort sort = new Sort(Direction.DESC, "createDate");
//		PageRequest request = new PageRequest(i, 10, sort);
//		return inboxRepo.findAllInbox(name, request);
//	}
	

	@Override
	public Page<Inbox> inboxGetChat(String text, int page) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(page, 10, sort);
		return inboxRepo.inboxGetChat(text, request);
	}
	
	@Override
	public Page<InboxList> findInboxList(String text, int page) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(page, 10, sort);
		return inboxListRepo.findInboxList(text, request);
	}
}

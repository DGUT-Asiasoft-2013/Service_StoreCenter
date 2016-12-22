package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Inbox;
import org.everyday2point5.fivestore.repository.IInboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}

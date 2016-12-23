package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.Inbox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IInboxRepository extends PagingAndSortingRepository<Inbox, Integer>{

	//@Query("select ibx1.id as  from Inbox ibx1 where ibx1.id = ?1")
	@Query("FROM Inbox inbox1 WHERE inbox1.createDate in (select max(inbox.createDate) as createDate from Inbox inbox WHERE inbox.rec_name = ?1 OR inbox.send_name = ?1 )GROUP BY inbox1.sign")
	Page<Inbox> findAllInbox(String name,Pageable request);
	
	@Query("FROM Inbox inbox WHERE inbox.sign = ?1 ")
	Page<Inbox> searchInbox(String name,Pageable request);
}

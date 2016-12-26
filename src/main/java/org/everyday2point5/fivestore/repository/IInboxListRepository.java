package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.InboxList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface IInboxListRepository extends PagingAndSortingRepository<InboxList, Integer> {

	@Query("FROM InboxList inboxlist WHERE inboxlist.rec_name = ?1 OR inboxlist.send_name = ?1")
	Page<InboxList> findInboxList(String name, Pageable request);
	
	@Query("from InboxList inboxlist where inboxlist.sign = ?1")
	InboxList findInboxListBySign(String sign);
	
}

package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.Inbox;
import org.everyday2point5.fivestore.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IInboxRepository extends PagingAndSortingRepository<Inbox, Integer>{

}

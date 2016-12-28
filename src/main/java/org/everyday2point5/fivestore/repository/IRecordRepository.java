package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecordRepository extends PagingAndSortingRepository<Record, Integer>{

	@Query("from  Record record where record.buyer_id = ?1")
	Page<Record> findAllRecord(Integer uid, Pageable request);
}

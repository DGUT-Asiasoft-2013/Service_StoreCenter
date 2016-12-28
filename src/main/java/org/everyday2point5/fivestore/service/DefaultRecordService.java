package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Record;
import org.everyday2point5.fivestore.repository.IRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class DefaultRecordService implements IRecordService{

	@Autowired 
	IRecordRepository repo;
	@Override
	public Record save(Record record) {	
		
		return repo.save(record);
	}
	@Override
	public Page<Record> findAllMyRecord(Integer uid, int page) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(page, 10, sort);
		return repo.findAllRecord(uid, request);
	}
	@Override
	public Record findOne(int record_id) {
		
		return repo.findOne(record_id);
	}

}

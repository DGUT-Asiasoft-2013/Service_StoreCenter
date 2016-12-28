package org.everyday2point5.fivestore.service;

import org.everyday2point5.fivestore.entity.Record;
import org.springframework.data.domain.Page;

public interface IRecordService {

	Record save(Record record);
	Page<Record> findAllMyRecord(Integer uid, int page);
	Record findOne(int record_id);
}

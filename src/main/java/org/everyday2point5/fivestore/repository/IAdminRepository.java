package org.everyday2point5.fivestore.repository;

import org.everyday2point5.fivestore.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepository extends PagingAndSortingRepository<Admin, Integer>{

}

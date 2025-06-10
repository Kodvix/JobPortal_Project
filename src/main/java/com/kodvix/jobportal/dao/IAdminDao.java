package com.kodvix.jobportal.dao;

import com.kodvix.jobportal.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminDao extends JpaRepository<Admin, Long> {

	@Query(value = "select admin_seq.currval from dual", nativeQuery = true)
	Long getCurrentSeriesId();

	public Admin findByUserName(String userName);

	public boolean existsByUserName(String userName);
}

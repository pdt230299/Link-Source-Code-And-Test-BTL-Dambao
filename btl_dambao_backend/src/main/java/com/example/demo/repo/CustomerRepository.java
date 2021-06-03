package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	// dùng để check login
	
	public List<Customer> findByTen(String ten);
	
	@Transactional
	@Query(value="Select * from customer where username = ?1 and password = ?2",
			nativeQuery=true) 
	Customer findStaffByUsernameAndPassword(String username, String password);
}

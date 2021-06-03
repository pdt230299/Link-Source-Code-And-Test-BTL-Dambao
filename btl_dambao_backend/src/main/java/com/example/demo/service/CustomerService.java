package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	@Autowired
    private CustomerRepository repo;
	
	// lấy danh sách tất cả khách hàng
	public List<Customer> getListAll() {
        return (List<Customer>) repo.findAll();
    }
     
	// lưu thông tin khách hàng
    public Customer save(Customer customer) {
        return repo.save(customer);
    }
     
    // lấy thông tin khách hàng theo id
    public Customer getById(Long id) {
        try {
        	return repo.findById(id).get();
        }catch(Exception e) {
        	System.out.print("Error from customerService : " + e.getMessage());
        }
        return new Customer();
    }
     
    // xóa khách hàng
    public void delete(Long id) {
    	repo.deleteById(id);
    }
    
    
    // check login
    public Customer checkLogin(Customer customer) {
    	customer = repo.findStaffByUsernameAndPassword(customer.getUsername(), customer.getPassword());
    	if (customer != null) {
    		System.out.print(customer);
    		return customer;
    	}
    	return new Customer();
    }
    public List<Customer> findByTen(String ten) {
        return (List<Customer>) repo.findByTen(ten);
    }
}

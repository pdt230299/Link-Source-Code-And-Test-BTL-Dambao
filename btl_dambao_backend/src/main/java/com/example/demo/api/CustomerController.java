package com.example.demo.api;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;


@RestController
@RequestMapping(path = "/customers", produces = "application/json")
@CrossOrigin(origins = "*")

public class CustomerController {
	private CustomerService khService;

	public CustomerController(CustomerService khService) {
		this.khService = khService;
	}
	// get all
	@GetMapping
	public List<Customer> getAllCustomer(){
		return khService.getListAll();
	}
//	Get By Name
	@GetMapping("/ten/{ten}")
	public List<Customer> getAllCustomerByTen(@PathVariable("ten") String ten){
		if(ten == null) {
			return null;
		}
		if(ten == "") {
			return khService.getListAll();
		}
		return khService.findByTen(ten);
	}
	// Get by id
	@GetMapping("/{id}")
	public Customer getById(@PathVariable("id") Long id) {
		Customer customer = khService.getById(id);
		if(id == null) {
			return null;
		}
		if (customer != null) {
			return customer;
		}
		return null;
	}
	
	// Thêm khách hàng
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer postCustomer(@RequestBody Customer customer) {
		if(customer == null) {
			return null;
		}
		khService.save(customer);
		return customer;
	} 
	
	// Xóa khách hàng
	@DeleteMapping("/delete/{id}")
	public void deleteCustomer(@PathVariable(name = "id") Long id) {
	    khService.delete(id);
	}
	
	// Sửa thông tin khách hàng
	@PostMapping(consumes="application/json", path = "/put" )
	public Customer putCustomer(@RequestBody Customer customer) {
		customer = khService.save(customer);// save gì thì trả về đó
		System.out.println(customer.toString());
		return customer;
	}
	
	// kiểm tra đăng nhập
	@PostMapping(consumes="application/json", path = "/login")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer checkLoginKhachHang(@RequestBody  Customer kh) {
		kh = khService.checkLogin(kh);
		return kh;
	}

}

package com.example.demo.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepository;




@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
class CustomerControllerTest {

	@Autowired
	CustomerController customerController;
	CustomerRepository repo;

//	Lấy tất cả Khách hàng 
//	@Test
//	public void testGetAllCustomerTonTai() {
//		List<Customer> lisCustomerTest = customerController.getAllCustomer();
//		Assertions.assertEquals(8, lisCustomerTest.size());
//	}
//	//Lấy Khách hàng theo id ko tồn tại
//	@Test
//	public void testGetByIdKoTonTai() {
//		Customer customer = customerController.getById((long) 10);
//		Assertions.assertEquals(customer.getIdkhachhang(), null);
//	}
//	//Lấy Khách hàng theo id null
//	@Test
//	public void testGetByIdNull() {
//		Customer customer = customerController.getById(null);
//		Assertions.assertEquals(customer, null);
//	}
//	//Lấy Khách hàng theo id tồn tại
//	@Test
//	public void testGetByIdTonTai() {
//		Customer customer = customerController.getById((long) 3);
//		Assertions.assertEquals( 3,customer.getIdkhachhang());
//	}
//	//Lấy Khách hàng theo Tên
//	@Test
//	public void testFindByNameTonTai() {
//		List<Customer> listKhachHang = customerController.getAllCustomerByTen("Vũ Tài Linh");
//		Customer customer = listKhachHang.get(0);
//		Assertions.assertEquals(customer.getTen(), "Vũ Tài Linh");
//	}
//	//Lấy Khách hàng theo Tên null
//	@Test
//	public void testFindByNameNull() {
//		List<Customer> listKhachHang = customerController.getAllCustomerByTen(null);
//		Assertions.assertEquals(listKhachHang, null);
//	}
//	//Lấy Khách hàng theo Tên bỏ trống
//	@Test
//	public void testFindByNameBoTrong() {
//		List<Customer> listKhachHang = customerController.getAllCustomerByTen("");
//		List<Customer> lisCustomerTest = customerController.getAllCustomer();
//		Assertions.assertEquals(listKhachHang.size(), lisCustomerTest.size());
//	}
//	//Lấy Khách hàng theo Tên ko tồn tại
//	@Test
//	public void testFindByNameKoTonTai() {
//		List<Customer> listKhachHang = customerController.getAllCustomerByTen("Vũ Tài Linhh");
//		Assertions.assertEquals(listKhachHang, new ArrayList<Customer>());
//	}
//		// trường hợp mặt hàng null
//		@Test
//		public void addKhachHangNULL() {
//			Customer customer = null;
//			customer = customerController.postCustomer(customer);
//			Assertions.assertEquals(customer, null);
//		}
//		@Test
//		public void addKhachHangSuccess() {
//			Customer customer = new Customer();
//			customer.setTen("Tùng");
//			customer.setCmnd("987654321");
//			customer.setDiachi("Thanh Oai");
//			customer.setPassword("1234");
//			customer.setUsername("tung123");
//			customerController.postCustomer(customer);
//			Assertions.assertEquals(customer, customerController.getById((long) 29)); // chú ý id tự tăng
//		}
		@Test
		public void testUpdate () {
			Customer customer = new Customer();
			customer.setIdkhachhang((long) 3); 
			customer.setTen("Vũ Tài Linh");
			customer.setCmnd("12564557851");
			customer.setDiachi("Hà Nội");
			customer.setPassword("123");
			customer.setUsername("linh2");
			customerController.putCustomer(customer);
			Assertions.assertEquals(customer.getTen(), customerController.getById((long) 3).getTen());
		}
//		@Test
//		public void testDelete () {
//			customerController.deleteCustomer((long) 3);
//			Customer customer = customerController.getById((long) 3);
//			Assertions.assertEquals(customer.getIdkhachhang(), null);
//		}
		
}

package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import com.example.demo.model.Customer;
import com.example.demo.model.DonHang;


@Controller
public class HomeController {
	private RestTemplate rest = new RestTemplate();
	
	private Environment env;
	@Autowired
	HomeController(Environment env){
		this.env = env;
	}
	
	@GetMapping("/login")
	public String showFormKHLogin(Model model, HttpSession session) {
		session.removeAttribute("khachhang");
		model.addAttribute("khachhang",new Customer());
		return "formkhlogin";
	}
	// Xem danh sách tất cả khách hàng trong hệ thống
	@GetMapping("/customers")
	public String getListKhachHang(Model model) {
		List<Customer> listKhachHang = 
				Arrays.asList(rest.getForObject("http://localhost:8080/customers",Customer[].class));
	    model.addAttribute("listKhachHang", listKhachHang);
		return "qlkhachhang";
	}
	@GetMapping("/donhang")
	public String getListDonHang(Model model) {
		List<DonHang> listDonHang = 
				Arrays.asList(rest.getForObject("http://localhost:8080/donhang",DonHang[].class));
	    model.addAttribute("listDonHang", listDonHang);
		return "qldonhang";
	}

		

}

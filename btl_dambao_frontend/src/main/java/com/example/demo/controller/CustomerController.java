package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/customers")
public class CustomerController {
	
	private RestTemplate rest = new RestTemplate();
	
	@Autowired
	public CustomerController() {
		
	}

	// Mở view thêm mới khách hngf
	@GetMapping("/new")
	public String showNewCustomerPage(Model model) {
	    Customer kh = new Customer();
	    model.addAttribute("khachhang", kh);
	    return "new_khachhang";
	}
	
	// Post khách hàng để thêm mới
	@PostMapping
	public String saveCustomer(@ModelAttribute("khachhang") Customer khachhang, Model model) {
		boolean Loi = false;
		if(khachhang.getUsername().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_username", "Vui lòng điền tên đăng nhập!");
		}
		if(khachhang.getPassword().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_password", "Vui lòng điền mật khẩu!");
		}
		if(khachhang.getCmnd().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_cmnd", "Vui lòng điền CMND!");
		}else if(!khachhang.getCmnd().trim().matches("-?\\d+(\\.\\d+)?")) {
			Loi = true;
			model.addAttribute("error_cmnd", "Vui lòng điền CMND không có chữ !");
		}
		if(khachhang.getTen().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_ten", "Vui lòng điền tên của khách hàng!");
		}
		if(khachhang.getTen().trim().matches(".*\\d.*")) {
			Loi = true;
			model.addAttribute("error_ten", "Vui lòng điền tên của khách hàng không có số!");
		}
		if(khachhang.getDiachi().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_diachi", "Vui lòng điền địa chỉ!");
		}
		if(Loi) {
			return "new_khachhang";
		}
		rest.postForObject("http://localhost:8080/customers",khachhang, Customer.class);
	    return "redirect:/customers";
	}
	
	// Xóa khách hàng
	@GetMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable(name = "id") Long id) {
		rest.delete("http://localhost:8080/customers/delete/{id}", id);
		return "redirect:/customers";
	}
	
	// Mở view edit
	@GetMapping("/edit/{id}")
	public ModelAndView editCustomer(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_khachhang");
		Customer khachhang = rest.getForObject("http://localhost:8080/customers/{id}",Customer.class, id);
	    mav.addObject("khachhang", khachhang);
	    return mav;
	}
	
	
	// lưu thông tin edit
	@PostMapping("/save_edit")
	public String saveEditCustomer(@ModelAttribute("khachhang") Customer khachhang, Model model) {
		boolean Loi = false;
		if(khachhang.getPassword().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_password", "Vui lòng điền mật khẩu!");
		}
		if(khachhang.getCmnd().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_cmnd", "Vui lòng điền CMND!");
		}else if(!khachhang.getCmnd().trim().matches("-?\\d+(\\.\\d+)?")) {
			Loi = true;
			model.addAttribute("error_cmnd", "Vui lòng điền CMND không có chữ !");
		}
		if(khachhang.getTen().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_ten", "Vui lòng điền tên của khách hàng!");
		}
		if(khachhang.getTen().trim().matches(".*\\d.*")) {
			Loi = true;
			model.addAttribute("error_ten", "Vui lòng điền tên của khách hàng không có số!");
		}
		if(khachhang.getDiachi().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_diachi", "Vui lòng điền địa chỉ!");
		}
		if(Loi) {
			return "edit_khachhang";
		}
		khachhang = rest.postForObject("http://localhost:8080/customers/put",khachhang, Customer.class);
	    return "redirect:/customers";
	    
	}
	
	// kiểm tra đăng nhập khi kh ấn login trên form login
	@PostMapping("/home")
	public String customerLogin(@ModelAttribute("khachhang") Customer khachhang, Model model,HttpSession session) {
		boolean Loi = false;
		if(khachhang.getUsername().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_username", "Vui lòng điền tên đăng nhập!");
		}
		if(khachhang.getPassword().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_password", "Vui lòng điền mật khẩu!");
		}else {
			khachhang = rest.postForObject("http://localhost:8080/customers/login",khachhang, Customer.class);
			if(khachhang.getUsername() == null) {
				Loi = true;
				model.addAttribute("error_both", "Thông tin đăng nhập KHÔNG chính xác vui lòng kiểm tra lại !");
			}
		}
			
		if(Loi) {
			return "formkhlogin";
		}
		if (khachhang.getUsername() != null) {
			model.addAttribute("khachhang", khachhang);
			session.setAttribute("khachhang", khachhang);
			return "redirect:/customers";
		}
	    return "formkhlogin";
	    
	}
	//des
	@GetMapping(path = "/search")
	public String searchCustomer(@ModelAttribute("inputSearch") String ten, Model model) {
		String url = "";
		if(ten.isEmpty()) {
			url = "redirect:/customers";
		}
		else {
			List<Customer> listKhachHang = Arrays.asList(rest.getForObject("http://localhost:8080/customers/ten/{ten}",Customer[].class,ten));
				model.addAttribute("listKhachHang", listKhachHang);
				url = "qlkhachhang";

		}
		return url;
	}
}

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
import com.example.demo.model.DonHang;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/donhang")
public class DonHangController {
	
	private RestTemplate rest = new RestTemplate();
	
	@Autowired
	public DonHangController() {
		
	}

	// Mở view thêm mới don hang
	@GetMapping("/new")
	public String showNewDonHangPage(Model model) {
	    DonHang dh = new DonHang();
	    model.addAttribute("donhang", dh);
	    return "new_donhang";
	}
	
	// Post don hàng để thêm mới
	@PostMapping
	public String saveDonHang(@ModelAttribute("donhang") DonHang donhang, Model model) {
		boolean Loi = false;
		if(donhang.getTenkhachhang().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_tenkhachhang", "Vui lòng điền tên khách hàng!");
		}
		if(donhang.getTenkhachhang().trim().matches(".*\\d.*")) {
			Loi = true;
			model.addAttribute("error_tenkhachhang", "Vui lòng điền tên của khách hàng không có số!");
		}
		if(donhang.getTenmathang().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_tenmathang", "Vui lòng điền tên mặt hàng!");
		}
		if(donhang.getSoluong().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_soluong", "Vui lòng điền số lượng sản phẩm!");
		}else if(!donhang.getSoluong().trim().matches("-?\\d+(\\.\\d+)?")) {
			Loi = true;
			model.addAttribute("error_soluong", "Vui lòng điền số lượng không có chữ !");
		}else if(Integer.parseInt(donhang.getSoluong()) > 100) {
			Loi = true;
			model.addAttribute("error_soluong", "Số lượng sản phẩm không hợp lệ nhỏ hơn 100!");
		}
		if(Loi) {
			return "new_donhang";
		}
		rest.postForObject("http://localhost:8080/donhang",donhang, DonHang.class);
	    return "redirect:/donhang";
	}
	
	// Xóa don hàng
	@GetMapping("/delete/{id}")
	public String deleteDonHang(@PathVariable(name = "id") Long id) {
		rest.delete("http://localhost:8080/donhang/delete/{id}", id);
		return "redirect:/donhang";
	}
	
	// Mở view edit
	@GetMapping("/edit/{id}")
	public ModelAndView editDonHang(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_donhang");
		DonHang donhang = rest.getForObject("http://localhost:8080/donhang/{id}",DonHang.class, id);
	    mav.addObject("donhang", donhang);
	    return mav;
	}
	
	
	// lưu thông tin edit
	@PostMapping("/save_edit")
	public String saveEditCustomer(@ModelAttribute("donhang") DonHang donhang, Model model) {
		boolean Loi = false;
		if(donhang.getTenmathang().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_tenmathang", "Vui lòng điền tên mặt hàng!");
		}
		if(donhang.getSoluong().trim().equals("")) {
			Loi = true;
			model.addAttribute("error_soluong", "Vui lòng điền số lượng sản phẩm!");
		}else if(!donhang.getSoluong().trim().matches("-?\\d+(\\.\\d+)?")) {
			Loi = true;
			model.addAttribute("error_soluong", "Vui lòng điền số lượng không có chữ !");
		}else if(Integer.parseInt(donhang.getSoluong()) > 100) {
			Loi = true;
			model.addAttribute("error_soluong", "Số lượng sản phẩm không hợp lệ nhỏ hơn 100!");
		}
		if(Loi) {
			return "edit_donhang";
		}
		donhang = rest.postForObject("http://localhost:8080/donhang/put",donhang, DonHang.class);
	    return "redirect:/donhang";
	    
	}
	//don hang search
	@GetMapping(path = "/search")
	public String searchDonHang(@ModelAttribute("inputSearch") String tenkhachhang, Model model) {
		String url = "";
		if(tenkhachhang.isEmpty()) {
			url = "redirect:/donhang";
		}
		else {
			List<DonHang> listDonHang = Arrays.asList(rest.getForObject("http://localhost:8080/donhang/tenkhachhang/{tenkhachhang}",DonHang[].class,tenkhachhang));
			model.addAttribute("listDonHang", listDonHang);
				url = "qldonhang";
		}
		return url;
	}
}
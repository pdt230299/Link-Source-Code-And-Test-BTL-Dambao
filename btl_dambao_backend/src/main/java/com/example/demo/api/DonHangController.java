package com.example.demo.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.model.DonHang;

import com.example.demo.service.DonHangService;

@RestController
@RequestMapping(path = "/donhang", produces = "application/json")
@CrossOrigin(origins = "*")

public class DonHangController {
	private DonHangService dhService;

	public DonHangController(DonHangService dhService) {
		this.dhService = dhService;
	}
	// get all
	@GetMapping
	public List<DonHang> getAllDonHang(){
		return dhService.getListAll();
	}
	// Get by id
	@GetMapping("/{id}")
	public DonHang getById(@PathVariable("id") Long id) {
		if(id == null) {
			return null;
		}
		DonHang donhang = dhService.getById(id);
		if (donhang != null) {
			return donhang;
		}
		return null;
	}
	@GetMapping("/tenkhachhang/{tenkhachhang}")
	public List<DonHang> getAllDonHangByTen(@PathVariable("tenkhachhang") String tenkhachhang){
		if(tenkhachhang == null) {
			return null;
		}
		if(tenkhachhang == "") {
			return dhService.getListAll();
		}
		return dhService.findByTen(tenkhachhang);
	}
	// Thêm don  hàng
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public DonHang postDonHang(@RequestBody DonHang donhang) {
		if(donhang == null) {
			return null;
		}
		dhService.save(donhang);
		return donhang;
	} 
	
	// Xóa don  hàng
	@DeleteMapping("/delete/{id}")
	public void deleteDonHang(@PathVariable(name = "id") Long id) {
		dhService.delete(id);
	}
	
	// Sửa thông tin don hàng
	@PostMapping(consumes="application/json", path = "/put" )
	public DonHang putDonHang(@RequestBody DonHang donhang) {
		donhang = dhService.save(donhang);// save gì thì trả về đó
		System.out.println(donhang.toString());
		return donhang;
	}
}

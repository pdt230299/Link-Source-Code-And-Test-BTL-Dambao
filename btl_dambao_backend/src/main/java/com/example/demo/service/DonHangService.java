package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.DonHang;
import com.example.demo.repo.DonHangRepository;

@Service
@Transactional
public class DonHangService {
	@Autowired
    private DonHangRepository repo;
	
	// lấy danh sách tất cả don hàng
	public List<DonHang> getListAll() {
        return (List<DonHang>) repo.findAll();
    }
     
	// lưu thông tin don hàng
    public DonHang save(DonHang donhang) {
        return repo.save(donhang);
    }
     
    // lấy thông tin don hàng theo id
    public DonHang getById(Long id) {
        try {
        	return repo.findById(id).get();
        }catch(Exception e) {
        	System.out.print("Error from customerService : " + e.getMessage());
        }
        return new DonHang();
    }
    public List<DonHang> findByTen(String tenkhachhang) {
        return (List<DonHang>) repo.findByTenkhachhang(tenkhachhang);
    }
    // xóa don hàng
    public void delete(Long id) {
    	repo.deleteById(id);
    }
    
}

package com.example.demo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;
import com.example.demo.model.DonHang;

@Repository
public interface DonHangRepository extends CrudRepository<DonHang, Long>{
	public List<DonHang> findByTenkhachhang(String tenkhachhang);
}

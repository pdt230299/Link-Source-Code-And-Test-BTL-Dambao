package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DonHang {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long iddonhang;
	private String tenkhachhang;
	private String tenmathang;
	private String soluong;
	public DonHang() {
		
	}
	public Long getIddonhang() {
		return iddonhang;
	}
	public String getSoluong() {
			return soluong;
	}
	public void setSoluong(String soluong) {
	        this.soluong = soluong;
	}
	public String getTenkhachhang() {
		return tenkhachhang;
	}
	public void setIddonhang(Long iddonhang) {
		this.iddonhang = iddonhang;
	}
	public void setTenkhachhang(String tenkhachhang) {
	        this.tenkhachhang = tenkhachhang;
	}
	public String getTenmathang() {
		return tenmathang;
	}
	public void setTenmathang(String tenmathang) {
	    this.tenmathang = tenmathang;
	}
}

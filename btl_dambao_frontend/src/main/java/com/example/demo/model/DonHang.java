package com.example.demo.model;



import lombok.Data;

@Data
public class DonHang {
	
	private Long iddonhang;
	private String tenkhachhang;
	private String tenmathang;
	private String soluong;
	public DonHang() {
		
	}
	
	public void setIddonhang(Long iddonhang) {
		this.iddonhang = iddonhang;
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

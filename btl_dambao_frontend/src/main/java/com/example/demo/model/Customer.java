package com.example.demo.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Customer {
	
	private Long idkhachhang;
	private String username;
	private String password;
	private String cmnd;
	private String ten;
	private String diachi;
	
	
	
	public void setIdkhachhang(Long idkhachhang) {
		this.idkhachhang = idkhachhang;
	}

	public Customer() {
			
		}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	public String getCmnd() {
		return cmnd;
	}
	public Long getIdkhachhang() {
		return idkhachhang;
	}
	
	public String getDiachi() {
		return diachi;
	}
	
	public String getTen() {
		return ten;
	}

	  public void setUsername(String username) {
	        this.username = username;
	    }
	  public void setTen(String ten) {
	        this.ten = ten;
	    }
	
	  public void setPassword(String password) {
	        this.password = password;
	    }
	
	  public void setCmnd(String cmnd) {
	        this.cmnd = cmnd;
	    }
		
	  public void setDiachi(String diachi) {
		      this.diachi = diachi;
		  
	  	}
}

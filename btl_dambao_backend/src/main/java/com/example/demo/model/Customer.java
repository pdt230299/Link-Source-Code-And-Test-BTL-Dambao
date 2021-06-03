package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idkhachhang;
	private String username;
	private String password;
	private String cmnd;
	private String ten;
	private String diachi;
	
	public Customer() {
		
	}

	public Customer(long l, String string, String string2, String string3, String string4, String string5) {
		// TODO Auto-generated constructor stub
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
	public void setIdkhachhang(Long idkhachhang) {
		this.idkhachhang = idkhachhang;
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
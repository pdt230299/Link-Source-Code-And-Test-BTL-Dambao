package com.example.demo.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.DonHang;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
class DonHangControllerTest {

	@Autowired
	DonHangController donhangController;
//
////	Lấy tất cả Đơn hàng 
//	@Test
//	public void testGetAllDonHangTonTai() {
//		List<DonHang> lisDonHangTest = donhangController.getAllDonHang();
//		Assertions.assertEquals(6, lisDonHangTest.size());
//	}
//	//Lấy Đơn hàng theo id ko tồn tại
//	@Test
//	public void testGetByIdKoTonTai() {
//		DonHang DonHang = donhangController.getById((long) 10);
//		Assertions.assertEquals(DonHang.getIddonhang(), null);
//	}
//	//Lấy Đơn hàng theo id null
//	@Test
//	public void testGetByIdNull() {
//		DonHang DonHang = donhangController.getById(null);
//		Assertions.assertEquals(DonHang, null);
//	}
//	//Lấy Đơn hàng theo id tồn tại
//	@Test
//	public void testGetByIdTonTai() {
//		DonHang DonHang = donhangController.getById((long) 3);
//		Assertions.assertEquals( 3,DonHang.getIddonhang());
//	}
//	//Lấy Đơn hàng theo Tên
//	@Test
//	public void testFindByNameTonTai() {
//		List<DonHang> listDonHang = donhangController.getAllDonHangByTen("Vũ Tài Linh");
//		DonHang DonHang = listDonHang.get(0);
//		Assertions.assertEquals(DonHang.getTenkhachhang(), "Vũ Tài Linh");
//	}
//	//Lấy Đơn hàng theo Tên null
//	@Test
//	public void testFindByNameNull() {
//		List<DonHang> listDonHang = donhangController.getAllDonHangByTen(null);
//		Assertions.assertEquals(listDonHang, null);
//	}
//	//Lấy Đơn hàng theo Tên bỏ trống
//	@Test
//	public void testFindByNameBoTrong() {
//		List<DonHang> listDonHang = donhangController.getAllDonHangByTen("");
//		List<DonHang> lisDonHangTest = donhangController.getAllDonHang();
//		Assertions.assertEquals(listDonHang.size(), lisDonHangTest.size());
//	}
//	//Lấy Đơn hàng theo Tên ko tồn tại
//	@Test
//	public void testFindByNameKoTonTai() {
//		List<DonHang> listDonHang = donhangController.getAllDonHangByTen("Vũ Tài Linhh");
//		Assertions.assertEquals(listDonHang, new ArrayList<DonHang>());
//	}
//		// trường hợp mặt hàng null
//		@Test
//		public void addDonHangNULL() {
//			DonHang DonHang = null;
//			DonHang = donhangController.postDonHang(DonHang);
//			Assertions.assertEquals(DonHang, null);
//		}
//		@Test
//		public void addKhachHangSuccess() {
//			DonHang DonHang = new DonHang();
//			DonHang.setTenkhachhang("Phùng Đình Tùng");
//			DonHang.setSoluong("10");
//			DonHang.setTenmathang("Nấm kim châm");
//			donhangController.postDonHang(DonHang);
//			Assertions.assertEquals(DonHang, donhangController.getById((long) 10)); // chú ý id tự tăng
//		}
		@Test
		public void testUpdate () {
			DonHang DonHang = new DonHang();
			DonHang.setIddonhang((long) 1);
			DonHang.setTenkhachhang("Nguyễn Văn Tú");
			DonHang.setSoluong("10");
			DonHang.setTenmathang("Cháo");
			donhangController.putDonHang(DonHang);
			Assertions.assertEquals(DonHang.getTenkhachhang(), donhangController.getById((long) 1).getTenkhachhang());
		}
//		@Test
//		public void testDelete () {
//			donhangController.deleteDonHang((long) 3);
//			DonHang DonHang = donhangController.getById((long) 3);
//			Assertions.assertEquals(DonHang.getIddonhang(), null);
//		}
//		

}

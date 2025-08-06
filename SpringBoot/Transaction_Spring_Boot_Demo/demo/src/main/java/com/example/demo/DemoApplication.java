package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.service.TransactionDemoService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	private final TransactionDemoService transactionDemoService;
	@Autowired
	public DemoApplication(TransactionDemoService transactionDemoService) {
		this.transactionDemoService = transactionDemoService;
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Override
	public void run(String...args) throws Exception{
		//DEMO VỀ TRANSACTION 
		// 1. Trường hợp Transaction THÀNH CÔNG 
        
        transactionDemoService.createAuthor("TacGia ThanhCong", false);
        System.out.println("=> Đã kiểm tra DB, tác giả 'TacGia ThanhCong' tồn tại.");

        // 2. Trường hợp Transaction THẤT BẠI (Rollback) 
        System.out.println("\n--- 2. Trường hợp Transaction THẤT BẠI (Rollback) ---");
        try {
            // Service sẽ ném ra một Exception ở đây
            transactionDemoService.createAuthor("TacGia ThatBai", true);
        } catch (Exception e) {
            // Bắt lỗi để chương trình không bị dừng đột ngột
            System.err.println(e.getMessage());
            System.err.println("Giao dịch đã được ROLLBACK. Tác giả 'TacGia ThatBai' sẽ không có trong DB.");
        }
	}
	
}

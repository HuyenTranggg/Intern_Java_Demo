package com.springtransaction.SpringTransaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springtransaction.SpringTransaction.dao.ProductDao;
import com.springtransaction.SpringTransaction.entity.Product;

@Service
public class OuterService {

    @Autowired
    private InnerService innerService;
    @Autowired
    private ProductDao productDao;
    
    // 1. REQUIRED
    @Transactional
    public void testRequired_InnerFails() {
        productDao.save(new Product("Outer-Product"));
        try {
            innerService.required_method_and_fail(); // nay la 1 phan cua transaction ban dau, khi loi xay ra o day thi no rollback toan bo
        } catch (Exception e) {
            System.out.println("Outer đã bắt được lỗi từ Inner: " + e.getMessage());
        }
    }
    // 2. REQUIRES_NEW
    @Transactional
    public void testRequiresNew_OuterFails() {
        innerService.requires_new_method(); // Inner chạy và commit trong transaction riêng
        // Outer bị lỗi sau khi Inner đã thành công
        throw new RuntimeException("Rollback từ Outer");
    }

    // 3. SUPPORTS
    @Transactional // Co transaction -> hoat dong giong nhau REQUIRED
    public void testSupports_WithOuterTransaction() {
        try {
            innerService.supports_method_and_fail();
        } catch (Exception e) {
            System.out.println("Outer đã bắt được lỗi từ Inner: " + e.getMessage());
        }
    }

    // Khong co transaction -> Hoat dong o che do auto-commit, hoat dong ma khong can transaction, innerService co nem ra transaction thi cung se duoc luu trong db
    public void testSupports_WithoutOuterTransaction() { 
        try {
            innerService.supports_method_and_fail();
        } catch (Exception e) {
            System.out.println("Outer đã bắt được lỗi từ Inner: " + e.getMessage());
        }
    }

    // 4. MANDATORY
    public void testMandatory_Fails() { // khong co @Transactional -> proxy nem ra exception, chuowng trinh dung, khong duoc luu trong DB
        innerService.mandatory_method();
    }

    // 5. NOT_SUPPORTED
    @Transactional
    public void testNotSupported_OuterFails() {
        productDao.save(new Product("Outer-Product"));
        innerService.not_supported_method(); // Inner chạy không có transaction -> auto-commit cap nhat vao db
        throw new RuntimeException("Rollback từ Outer");// nem ra exception ctrinh dung nhung innerService.not_supported_method da duoc cap nhat
    }

    // 6. NEVER - bat buoc khong duoc ton tai transaction nao
    @Transactional // co transaction, khi proxy goi never_method -> nem ra exception, ctrinh bi dung
    public void testNever_Fails() {
        innerService.never_method();
    }

    // 7. NESTED - transaction con khong anh huong den transaction cha khi co exception
    @Transactional
    public void testNested_InnerFails() {
        productDao.save(new Product("Outer-Product"));
        // Tao ra 1 savepoint truoc khi thuc hien innerService.nested_method_and_fail
        try {
            innerService.nested_method_and_fail(); // Co loi se rollback ve savepoint
        } catch (Exception e) {
            System.out.println("Outer đã bắt được lỗi từ Inner: " + e.getMessage());
            // Lỗi của inner không làm outer bị rollback
        }
    }
}
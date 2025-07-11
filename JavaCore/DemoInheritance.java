public class DemoInheritance{
	public static void main(String[] args) {
		BankAccount loan = new LoanAccount("ABC123","Nguyen Van A","Loan",0.001,10000000);
		BankAccount term = new TermSavingAccount("ABC123","Nguyen Van B","Saving",0.001,200000000,100000000);
		
	}
}

// Lớp abstract 
abstract class BankAccount{
	protected String soTaiKhoan;
	protected String tenChuTaiKhoan;
	protected String loaiTaiKhoan;
	protected double laiSuat;
	// constructor
	public BankAccount(String soTaiKhoan, String tenChuTaiKhoan, String loaiTaiKhoan, double laiSuat) {
		this.soTaiKhoan = soTaiKhoan;
		this.tenChuTaiKhoan = tenChuTaiKhoan;
		this.loaiTaiKhoan = loaiTaiKhoan;
		this.laiSuat = laiSuat;
	}
	// mỗi lớp abstact phải có ít nhất 1 phương thức trừu tượng
	public abstract void xuLyCuoiThang();
	public void printInfo() {
		System.out.print("So tai khoan" + this.soTaiKhoan);
		System.out.print("Ten chu tai khoan:" + this.tenChuTaiKhoan);
		System.out.print("Loai tai khoan:" + this.loaiTaiKhoan);
	}
}

// Lớp LoanAccount kế thừa lớp abstract BankAccount phải implement tất cả 
// phương thức abstract, có keyword final nó sẽ không được thừa kế bởi các lớp con nữanữa
final class LoanAccount extends BankAccount{
	protected double soTienVayBanDau;
	protected double soTienPhaiTraHienTai;
	public LoanAccount(String soTaiKhoan, String tenChuTaiKhoan, String loaiTaiKhoan, double laiSuat, double soTienVayBanDau) {
		super(soTaiKhoan, tenChuTaiKhoan, loaiTaiKhoan,laiSuat);
		this.soTienVayBanDau = soTienVayBanDau;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void xuLyCuoiThang() {
		// TODO Auto-generated method stub
		if(soTienPhaiTraHienTai == 0) return;
		this.soTienPhaiTraHienTai += this.soTienVayBanDau * this.laiSuat;
	}
	@Override
	public void printInfo() {
		super.printInfo(); // sử dụng super gọi hàm printInfo của lớp cha BankAcountBankAcount
		System.out.print("So tien da vay ban dau:" + this.soTienVayBanDau);
		System.out.print("So tien no hien tai phai tra" + this.soTienPhaiTraHienTai);
	}
}

// Lớp LoanAccount kế thừa lớp abstract BankAccount phải implement tất cả phương thức abstract,
class SavingAccount extends BankAccount{
	public SavingAccount(String soTaiKhoan, String tenChuTaiKhoan, String loaiTaiKhoan, double laiSuat, double soTienDaGui, double soDuBanDau) {
		super(soTaiKhoan, tenChuTaiKhoan, loaiTaiKhoan, laiSuat);
		this.soDu = soDuBanDau;
		this.soTienDaGui = soTienDaGui;
		
		// TODO Auto-generated constructor stub
	}
	protected double soDu;
	protected double tienLai;
	protected double soTienDaGui;
	protected double tienLaiThangNay;
	protected double soThangDaGui;
	@Override
	public void xuLyCuoiThang() {
		// TODO Auto-generated method stub
		this.tienLaiThangNay = tinhTienLaiHangThang();
		this.tienLai += tienLaiThangNay;
		this.soDu += this.tienLai;
	}
	
	public double tinhTienLaiHangThang() {
		return this.soTienDaGui * this.laiSuat;
	}
	
	// các lớp con sẽ không được kế thừa các phương thức có keyword final, static, private
	public final void inThongTinChiTiet() {
		super.printInfo();
		System.out.print("Tien lai thang nay:" + this.tienLaiThangNay);
		System.out.print("So du hien tai:" + this.soDu);
		System.out.print("Tong tien lai:" + this.tienLai);
	}
}

// lớp TermSavingAccount kế thừa từ lớp SavingAccount
class TermSavingAccount extends SavingAccount{
	
	public TermSavingAccount(String soTaiKhoan, String tenChuTaiKhoan, String loaiTaiKhoan, double laiSuat,
			double soTienDaGui, double soDuBanDau) {
		super(soTaiKhoan, tenChuTaiKhoan, loaiTaiKhoan, laiSuat, soTienDaGui, soDuBanDau);
		// TODO Auto-generated constructor stub
	}

    // Overloading: tên phương thức giống nhau, kiểu tham số trả về, số lượng tham số khác nhau, kiểu trả về của phương thức có thể giống hoặc khác nhau
    // Overrriding: Tên phương thức giống với lớp nhau, số lượng tham số và kiểu trả về của phương thức phải giống lớp cha.
	// overriding phương thức tinhTienLaiHangThang 
	@Override
	public double tinhTienLaiHangThang() {
		if(soTienDaGui < 100000000) return this.soTienDaGui*(this.laiSuat+0.006);
		else if(soTienDaGui >= 100000000 && soTienDaGui < 500000000) return this.soTienDaGui*(this.laiSuat+0.01);
		else return this.soTienDaGui*(this.laiSuat+0.05);
	}
	
	//Overloading 
    public void guiTien(double soTien) {
        if (soTien > 0) {
            this.soDu += soTien;
            System.out.println("Giao dich thanh cong. Da gui " + soTien);
            // Ghi log giao dịch đơn giản
        }
    }

    public void guiTien(double soTien, String ghiChu) {
        if (soTien > 0) {
            this.soDu += soTien;
            System.out.println("Giao dich thanh cong. Da gui " + soTien);
            System.out.println("Ghi chu: " + ghiChu);
            // Ghi log giao dịch với ghi chú chi tiết
        }
    }
    
}




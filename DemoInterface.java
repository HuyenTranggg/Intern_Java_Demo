public class DemoInterface{
	public static void DemoInterface() {
		GiamDoc A = new GiamDoc("Nguyen Van A", "Giam Doc", 50000000, 5,20000000);
		A.loiNhanCongTy = 150000000;
		GiamDoc.tinhThuong calBonus = new tinhThuongCuoiNam(); 
		// interface không thể tạo trực tiếp constructor nhưng có thể tạo qua class implement nó
		A.setBonusCalculator(calBonus);
		A.inThongTin();
	}
}

abstract class NhanVien{
	// abstract có thể chứa các biến non-final, interface thì không 
	protected String tenNhanVien;
	protected String chucVu;
	protected String maSoThue;
	protected double luongCoBan;
	protected double heSoLuong;
	
	//abstract có constructor, interface thì không
	public NhanVien(String tenNhanVien, String chucVu, double luongCoBan, double heSoLuong) {
		super();
		this.tenNhanVien = tenNhanVien;
		this.chucVu = chucVu;
		this.luongCoBan = luongCoBan;
		this.heSoLuong = heSoLuong;
	}
	
	public abstract double tinhLuong();
	
	// abstract có thể implement ngay trong lớp này còn interface thì không
	// abstract có thể chứa các phương thức protected, static, private, default còn interface chỉ chứa các phương thức publicpublic
	protected void inThongTin() {
		System.out.println("Ten nhan vien:" + this.tenNhanVien);
		System.out.println("Chuc vu:" + this.chucVu);
	}
}

interface QuanLy{
	String x = "abcabc"; // trình biên dịch luôn hiểu là public static final String x = 20
	
	public double tinhHoaHong();
}

interface QuyenHan{
	public boolean pheDuyetNganSach(double soTien, String lyDo);
}

// class GiamDoc thừa kế abstract class NhanVien, implement interface QuanLy, QuyenHan 
// có thể implement nhiều interface nhưng chỉ được thừa kế duy nhất 1 classclass
class GiamDoc extends NhanVien implements QuanLy,QuyenHan{
	public GiamDoc(String tenNhanVien, String chucVu, double luongCoBan, double heSoLuong, double phuCap) {
		super(tenNhanVien, chucVu, luongCoBan, heSoLuong);
		this.phuCap = phuCap;
		// TODO Auto-generated constructor stub
	}
	private double phuCap;
	public double loiNhanCongTy;
	public tinhThuong tienThuong;
	
	@Override
	public double tinhHoaHong() {
		// TODO Auto-generated method stub
		return this.loiNhanCongTy * 0.05;
	}
	@Override
	public double tinhLuong() {
		// TODO Auto-generated method stub
		return this.luongCoBan * this.heSoLuong + this.phuCap + this.tinhHoaHong();
	}
	@Override
	public boolean pheDuyetNganSach(double soTien, String lyDo) {
		// TODO Auto-generated method stub
		System.out.print("Giam doc:" + this.tenNhanVien + "da phe duyet ngan sach" + soTien + "cho" + lyDo);
		return true;
	}
	
	// Nested Interface cho việc tính thưởng cuối năm cho giám đốc dựa trên loiNhanCongTy
	public interface tinhThuong{
		public double tinhThuong(double loiNhanCongTy);
	}
	public void setBonusCalculator(tinhThuong calculator) {
		this.tienThuong = calculator;
	}
	@Override
	public void inThongTin() {
		super.inThongTin();
		System.out.println("Tien thuong cuoi nam:" + this.tienThuong);
	}
}

class tinhThuongCuoiNam implements GiamDoc.tinhThuong{

	@Override
	public double tinhThuong(double loiNhanCongTy) {
		// TODO Auto-generated method stub
		return loiNhanCongTy * 0.15;
	}
	
}



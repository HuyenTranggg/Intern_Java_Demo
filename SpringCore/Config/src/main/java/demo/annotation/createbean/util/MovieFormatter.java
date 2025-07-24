package demo.annotation.createbean.util;

// Giả sử đây là 1 class từ thư viện bên ngoài, không thể sửa code
public class MovieFormatter {
	private final String prefix;

	public MovieFormatter(String prefix) {
	    this.prefix = prefix;
	}
	
	public String format(String movieTitle) {
	    return prefix + " " + movieTitle;
	}
}
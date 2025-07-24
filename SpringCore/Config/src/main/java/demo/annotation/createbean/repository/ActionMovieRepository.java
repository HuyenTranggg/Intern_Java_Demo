package demo.annotation.createbean.repository;

import org.springframework.stereotype.Repository;

import demo.annotation.createbean.model.Movie;

import java.util.List;

// @Repository: Dùng để đánh dấu các lớp ở tầng truy cập dữ liệu (Data Access Layer - DAO)
// Bean này có tên mặc định là "actionMovieRepository"
@Repository("actionRepo") // Có thể đặt tên khác cho bean nếu muốn
public class ActionMovieRepository implements MovieRepository {
	public ActionMovieRepository() {
		System.out.println("Creating a bean with @Repository annotation");
	}
	
    @Override
    public List<Movie> findAll() {
        return List.of(new Movie("Die Hard"), new Movie("John Wick"));
    }
}
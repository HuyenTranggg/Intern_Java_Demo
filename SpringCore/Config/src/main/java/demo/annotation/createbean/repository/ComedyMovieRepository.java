package demo.annotation.createbean.repository;

import org.springframework.stereotype.Repository;

import demo.annotation.createbean.model.Movie;

import java.util.List;

// Bean này có tên mặc định là "comedyMovieRepository"
@Repository
public class ComedyMovieRepository implements MovieRepository {
	public ComedyMovieRepository() {
		System.out.println("Creating another bean with @Repository annotation with a custome name");
	}
	
    @Override
    public List<Movie> findAll() {
        return List.of(new Movie("The Hangover"), new Movie("Superbad"));
    }
}
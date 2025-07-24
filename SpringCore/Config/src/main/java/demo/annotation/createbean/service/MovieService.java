package demo.annotation.createbean.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import demo.annotation.createbean.model.Movie;
import demo.annotation.createbean.repository.MovieRepository;

// @Service: Dùng để đánh dấu các lớp ở tầng dịch vụ (Service Layer)
// Spring sẽ tự động phát hiện và tạo bean "movieService"
@Service
public class MovieService {

    private MovieRepository movieRepository;

    public void printAllMovies() {
        movieRepository.findAll().forEach(System.out::println);
    }
    
    // Các cách tạo bean với annotation
    /*
     * C3: Định nghĩa một @Bean bên trong một @Component (không phải @Configuration)
     */
    @Bean
    public Movie defaultMovie() {
        System.out.println("Creating a bean inside a @Component");
        return new Movie("Default Movie");
    }
}
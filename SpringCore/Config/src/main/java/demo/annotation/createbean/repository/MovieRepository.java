package demo.annotation.createbean.repository;

import java.util.List;

import demo.annotation.createbean.model.Movie;

public interface MovieRepository {
    List<Movie> findAll();
}

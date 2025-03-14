package org.magust.jee.persistence;

import jakarta.data.repository.CrudRepository;
import org.magust.jee.entity.Movie;

import java.util.List;
import java.util.stream.Stream;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    Stream<Movie> findAll();

    List<Movie> findByTitle(String title);
    List<Movie> findByDirector(String director);
    List<Movie> findByGenre(String genre);

    List<Movie> findByTitleAndDirector(String title, String director);
    List<Movie> findByTitleAndGenre(String title, String genre);
    List<Movie> findByDirectorAndGenre(String director, String genre);
    List<Movie> findByTitleAndDirectorAndGenre(String title, String director, String genre);
}

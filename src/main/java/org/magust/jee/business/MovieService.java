package org.magust.jee.business;

import jakarta.inject.Inject;
import org.magust.jee.dto.*;
import org.magust.jee.entity.Movie;
import org.magust.jee.exception.*;
import org.magust.jee.mapper.MovieMapper;
import org.magust.jee.persistence.MovieRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieService {

    @Inject
    private MovieRepository repository;

    public List<Movie> getAllMovies() {
        return (List<Movie>) repository.findAll();
    }

    public Movie getMovieById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Movie with ID " + id + " not found"));
    }

    public void createMovie(CreateMovie createMovie) {
        if (createMovie.duration() <= 0) {
            Map<String, String> errors = new HashMap<>();
            errors.put("duration", "Duration must be greater than 0");
            throw new ValidationException("Validation failed", errors);
        }

        Movie movie = MovieMapper.toMovie(createMovie);
        repository.save(movie);
    }

    public void updateMovie(UpdateMovie updateMovie, Long id) {
        Movie existingMovie = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Movie with ID " + id + " not found"));

        MovieMapper.updateMovieFromDTO(updateMovie, existingMovie);
        repository.save(existingMovie);
    }

    public void deleteMovie(Long id) {
        Movie movie = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Movie with ID " + id + " not found"));
        repository.delete(movie);
    }

    public List<MovieDTO> filterMovies(String title, String director, String genre) {
        List<Movie> filteredMovies;

        if (title != null && director != null && genre != null) {
            filteredMovies = repository.findByTitleAndDirectorAndGenre(title, director, genre);
        } else if (title != null && director != null) {
            filteredMovies = repository.findByTitleAndDirector(title, director);
        } else if (title != null && genre != null) {
            filteredMovies = repository.findByTitleAndGenre(title, genre);
        } else if (director != null && genre != null) {
            filteredMovies = repository.findByDirectorAndGenre(director, genre);
        } else if (title != null) {
            filteredMovies = repository.findByTitle(title);
        } else if (director != null) {
            filteredMovies = repository.findByDirector(director);
        } else if (genre != null) {
            filteredMovies = repository.findByGenre(genre);
        } else {
            filteredMovies = (List<Movie>) repository.findAll();
        }

        return filteredMovies.stream()
                .map(MovieMapper::toMovieDTO)
                .collect(Collectors.toList());
    }
}

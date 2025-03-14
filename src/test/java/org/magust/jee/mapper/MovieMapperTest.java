package org.magust.jee.mapper;

import org.junit.jupiter.api.Test;
import org.magust.jee.dto.CreateMovie;
import org.magust.jee.dto.MovieDTO;
import org.magust.jee.entity.Movie;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MovieMapperTest {

    @Test
    public void ToMovie() {
        CreateMovie createMovie = new CreateMovie("Test Movie", "Test Director", "Test Genre", "Test Description", LocalDate.now(), 120);

        Movie movie = MovieMapper.toMovie(createMovie);

        assertNotNull(movie);
        assertEquals("Test Movie", movie.getTitle());
        assertEquals("Test Director", movie.getDirector());
        assertEquals("Test Description", movie.getDescription());
        assertEquals(120, movie.getDuration());
    }

    @Test
    public void ToMovieDTO() {
        Movie movie = new Movie("Test Movie", "Test Director", "Test Description", LocalDate.now(), 120);

        MovieDTO movieDTO = MovieMapper.toMovieDTO(movie);

        assertNotNull(movieDTO);
        assertEquals("Test Movie", movieDTO.title());
        assertEquals("Test Director", movieDTO.director());
        assertEquals("Test Description", movieDTO.description());
        assertEquals(120, movieDTO.duration());
    }
}

package org.magust.jee.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.magust.jee.dto.CreateMovie;
import org.magust.jee.dto.UpdateMovie;
import org.magust.jee.exception.*;
import org.magust.jee.persistence.MovieRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void CreateMovieWithInvalidDuration() {
        CreateMovie createMovie = new CreateMovie(
                "Test Movie",
                "Action",
                "Director",
                "Description",
                LocalDate.of(2023, 1, 1),
                -10
        );

        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            movieService.createMovie(createMovie);
        });

        assertEquals("Validation failed", thrown.getMessage());
        assertTrue(thrown.getErrors().containsKey("duration"));
        assertEquals("Duration must be greater than 0", thrown.getErrors().get("duration"));
    }

    @Test
    public void deleteMovieWhenMovieDoesNotExist() {
        Long invalidMovieId = 999L;

        when(movieRepository.findById(invalidMovieId)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            movieService.deleteMovie(invalidMovieId);
        });

        assertEquals("Movie with ID " + invalidMovieId + " not found", thrown.getMessage());
    }

    @Test
    public void updateMovieWhenMovieDoesNotExist() {
        Long movieId = 1L;
        UpdateMovie updatedMovie = new UpdateMovie(
                "Updated Title",
                "Updated Genre",
                "Updated Director",
                "Updated Description",
                LocalDate.of(2024, 5, 10),
                120
        );

        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            movieService.updateMovie(updatedMovie, movieId);
        });

        assertEquals("Movie with ID " + movieId + " not found", thrown.getMessage());
    }
}

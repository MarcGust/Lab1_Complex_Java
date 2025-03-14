package org.magust.jee.mapper;

import org.magust.jee.dto.MovieDTO;
import org.magust.jee.dto.CreateMovie;
import org.magust.jee.dto.UpdateMovie;
import org.magust.jee.entity.Movie;

public class MovieMapper {

    public static MovieDTO toMovieDTO(Movie movie) {
        if (movie == null) {
            return null;
        }
        return new MovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getGenre(),
                movie.getDescription(),
                movie.getReleaseDate(),
                movie.getDuration()
        );
    }

    public static Movie toMovie(CreateMovie dto) {
        if (dto == null) {
            return null;
        }
        return new Movie(
                dto.title(),
                dto.director(),
                dto.description(),
                dto.releaseDate(),
                dto.duration()
        );
    }

    public static Movie updateMovieFromDTO(UpdateMovie dto, Movie existingMovie) {
        if (dto == null || existingMovie == null) {
            return null;
        }
        existingMovie.setTitle(dto.title());
        existingMovie.setDirector(dto.director());
        existingMovie.setGenre(dto.genre());
        existingMovie.setDescription(dto.description());
        existingMovie.setReleaseDate(dto.releaseDate());
        existingMovie.setDuration(dto.duration());
        return existingMovie;
    }
}

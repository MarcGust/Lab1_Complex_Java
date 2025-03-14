package org.magust.jee.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record UpdateMovie(
        @NotBlank(message = "Title is required") String title,
        @NotBlank(message = "Director is required") String director,
        @NotBlank(message = "Genre is required") String genre,
        @Size(max = 500, message = "Description cannot be longer than 500 characters") String description,
        @PastOrPresent(message = "Release date must be in the past or present") LocalDate releaseDate,
        @Min(value = 1, message = "Duration must be greater than 0 min") int duration
) {}

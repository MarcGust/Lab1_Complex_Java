package org.magust.jee.dto;

import java.time.LocalDate;

public record MovieDTO(
        Long id,
        String title,
        String director,
        String genre,
        String description,
        LocalDate releaseDate,
        int duration
) {}

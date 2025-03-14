package org.magust.jee.presentation;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.magust.jee.dto.MovieDTO;
import org.magust.jee.dto.CreateMovie;
import org.magust.jee.dto.UpdateMovie;
import org.magust.jee.business.MovieService;
import org.magust.jee.entity.Movie;

import java.util.List;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject
    private MovieService movieService;

    @GET
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GET
    @Path("/filter")
    public List<MovieDTO> filterMovies(@QueryParam("title") String title,
                                       @QueryParam("director") String director,
                                       @QueryParam("genre") String genre) {
        return movieService.filterMovies(title, director, genre);
    }

    @POST
    public Response createMovie(CreateMovie createMovie) {
        movieService.createMovie(createMovie);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateMovie(@PathParam("id") Long id, UpdateMovie updateMovie) {
        movieService.updateMovie(updateMovie, id);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMovie(@PathParam("id") Long id) {
        movieService.deleteMovie(id);
        return Response.noContent().build();
    }
}

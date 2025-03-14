package org.magust.jee.presentation;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MovieResourceTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void GetAllMovies() {
        given()
                .when()
                .get("/movies")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void GetMovieById() {
        Long movieId = 1L;

        given()
                .pathParam("id", movieId)
                .when()
                .get("/movies/{id}")
                .then()
                .statusCode(200)
                .body("title", equalTo("Test Movie Title"));
    }

    @Test
    public void CreateMovie() {
        String movieJson = "{ \"title\": \"Test Movie\", \"director\": \"Test Director\", \"genre\": \"Action\", \"description\": \"A test movie\", \"releaseDate\": \"2023-03-14\", \"duration\": 120 }";

        given()
                .contentType("application/json")
                .body(movieJson)
                .when()
                .post("/movies")
                .then()
                .statusCode(201);
    }

    @Test
    public void UpdateMovie() {
        String updatedMovieJson = "{ \"title\": \"Updated Movie\", \"director\": \"Updated Director\", \"genre\": \"Action\", \"description\": \"Updated description\", \"releaseDate\": \"2023-03-14\", \"duration\": 130 }";

        given()
                .contentType("application/json")
                .body(updatedMovieJson)
                .pathParam("id", 1L)
                .when()
                .put("/movies/{id}")
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Movie"));
    }

    @Test
    public void DeleteMovie() {
        given()
                .pathParam("id", 1L)
                .when()
                .delete("/movies/{id}")
                .then()
                .statusCode(204);
    }
}

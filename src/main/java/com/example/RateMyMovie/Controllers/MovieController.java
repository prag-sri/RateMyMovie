package com.example.RateMyMovie.Controllers;

import com.example.RateMyMovie.DTOs.*;
import com.example.RateMyMovie.Models.Movie;
import com.example.RateMyMovie.Services.Implementation.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class MovieController {

    @Autowired
    MovieServiceImpl movieService;

    @GetMapping("/longest-duration-movies")
    public ResponseEntity<List<MovieResponseDTO1>> getLongestDurationMovies()
    {
        List<MovieResponseDTO1> moviesList= movieService.getLongestDurationMovies();
        return new ResponseEntity<>(moviesList, HttpStatus.FOUND);
    }

    @PostMapping("/new-movie")
    public ResponseEntity<String> newMovie(@RequestBody() MovieRequestDTO movieRequestDTO)
    {
        movieService.newMovie(movieRequestDTO);
        return new ResponseEntity<>("SUCCESS!",HttpStatus.CREATED);
    }

    @GetMapping("/top-rated-movies")
    public ResponseEntity<List<MovieResponseDTO2>> getTopRatedMovies()
    {
        List<MovieResponseDTO2> moviesList= movieService.getTopRatedMovies();
        return new ResponseEntity<>(moviesList, HttpStatus.FOUND);
    }

    @GetMapping("/genre-movies-with-subtotals")
    public ResponseEntity<List<MovieWithView>> genreMoviesWithSubtotals()
    {
        List<MovieWithView> moviesList= movieService.genreMoviesWithSubtotals();
        return new ResponseEntity<>(moviesList, HttpStatus.OK);
    }

    @PostMapping("/update-runtime-minutes")
    public ResponseEntity<String> updateRunTimeMinutes()
    {
        movieService.updateRunTimeMinutes();
        return new ResponseEntity<>("UPDATED!", HttpStatus.OK);
    }
}

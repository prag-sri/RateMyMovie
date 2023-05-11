package com.example.RateMyMovie.Services;

import com.example.RateMyMovie.DTOs.MovieRequestDTO;
import com.example.RateMyMovie.DTOs.MovieResponseDTO1;
import com.example.RateMyMovie.DTOs.MovieResponseDTO2;
import com.example.RateMyMovie.DTOs.MovieWithView;
import com.example.RateMyMovie.Models.Movie;

import java.util.List;

public interface MovieService {

    public void newMovie(MovieRequestDTO movieRequestDTO);
    public List<MovieResponseDTO1> getLongestDurationMovies();
    public List<MovieResponseDTO2> getTopRatedMovies();
    public Movie findMovieByTconst(String tconst);
    public List<MovieWithView> genreMoviesWithSubtotals();
    public void updateRunTimeMinutes();
}

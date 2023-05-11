package com.example.RateMyMovie.Services.Implementation;

import com.example.RateMyMovie.DTOs.*;
import com.example.RateMyMovie.Models.Enums.Genre;
import com.example.RateMyMovie.Models.Enums.TitleType;
import com.example.RateMyMovie.Models.Movie;
import com.example.RateMyMovie.Models.Rating;
import com.example.RateMyMovie.Repositories.MovieRepository;
import com.example.RateMyMovie.Repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServiceImpl {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    RatingRepository ratingRepository;

    public void newMovie(MovieRequestDTO movieRequestDTO)
    {

//        Setting variables of Movies
        Movie movie= Movie.builder().
                primaryTitle(movieRequestDTO.getPrimaryTitle()).
                runtimeMinutes(movieRequestDTO.getRuntimeMinutes()).
                genres(movieRequestDTO.getGenres()).build();

        if(movieRequestDTO.getTitleType().equals(TitleType.MOVIE))
            movie.setTitleType("movie");
        else
            movie.setTitleType("short");

//        Setting tconst of movie
        String prefix= "tt";
        int num= Movie.getNum()+1;
        Movie.setNum();
        int length= Integer.toString(num).length();
        int i=0;
        while(i<(7-length))
        {
            prefix+= "0";
            i++;
        }
        prefix+= Integer.toString(num);
        movie.setTconst(prefix);

//        Setting rating variables
        Rating rating= Rating.builder().
                tconst(prefix).
                averageRating(movieRequestDTO.getAverageRating()).
                numVotes(movieRequestDTO.getNumVotes()).build();

//        Saving movie in the repository
        ratingRepository.save(rating);
        movieRepository.save(movie);

    }

    public List<MovieResponseDTO1> getLongestDurationMovies()
    {
//        Adding movies in the priority queue in descending order of runtime
        PriorityQueue<Movie> pq= new PriorityQueue<>(new SortRunTimeClass());

        List<MovieResponseDTO1> moviesList= new ArrayList<>();

//        Adding movies in priority queue until size is 10 or movie with greater runtime is encountered
        for(Movie movie: movieRepository.findAll())
        {
            if(pq.size()<10 || movie.getRuntimeMinutes()>pq.peek().getRuntimeMinutes())
                pq.add(movie);
        }

        while(!pq.isEmpty())
        {
            Movie movie= pq.poll();
            MovieResponseDTO1 movieResponseDTO1 = new MovieResponseDTO1(
                    movie.getTconst(),movie.getPrimaryTitle(), movie.getRuntimeMinutes(),movie.getGenres());
            moviesList.add(movieResponseDTO1);
        }

        return moviesList;
    }

    public List<MovieResponseDTO2> getTopRatedMovies()
    {
//        ratingsList will contains all ratings above 6.0 in sorted manner
        List<Rating> ratingsList= new ArrayList<>();

        List<MovieResponseDTO2> moviesList= new ArrayList<>();

//        Finding avg ratings of movies above 6.0
        for(Rating rating: ratingRepository.findAll())
        {
            if(rating.getAverageRating()>6.0)
                ratingsList.add(rating);
        }

//        Sorting ratingsList on the basis of avg ratings in descending order
        Collections.sort(ratingsList,new SortAvgRateClass());

//        Adding movies in the moviesList as per sorted ratings
        for(int i=0; i<ratingsList.size(); i++)
        {
            Rating rating= ratingsList.get(i);
            Movie movie= findMovieByTconst(rating.getTconst());

//            Creating movie response DTO
            MovieResponseDTO2 movieResponseDTO2= new MovieResponseDTO2(rating.getTconst(), movie.getPrimaryTitle(), rating.getAverageRating(), movie.getGenres());

            moviesList.add(movieResponseDTO2);
        }

        return moviesList;
    }

    public Movie findMovieByTconst(String tconst)
    {
        Movie requiredMovie= new Movie();
        for(Movie movie: movieRepository.findAll())
        {
            if(movie.getTconst().equals(tconst)) {
                requiredMovie= movie;
                break;
            }
        }
        return requiredMovie;
    }

    public List<MovieWithView> genreMoviesWithSubtotals()
    {
        return movieRepository.genreMoviesWithSubtotals();
    }

    public void updateRunTimeMinutes()
    {
        for(Movie movie: movieRepository.findAll())
        {
//            Checking if the movie genre is Documentary, Animation or other and then running separate queries
            if(movie.getGenres().equals(Genre.Documentary))
                movieRepository.updateDocRunTimeMinutes(movie);
            else if(movie.getGenres().equals(Genre.Animation))
                movieRepository.updateAnimeRunTimeMinutes(movie);
            else
                movieRepository.updateOthersRunTimeMinutes(movie);
        }
    }
}

//Class for sorting movies by runtime in Priority Queue
class SortRunTimeClass implements Comparator<Movie>{
    public int compare(Movie m1, Movie m2){
        return m2.getRuntimeMinutes()-m1.getRuntimeMinutes();
    }
}

//Class for sorting movies by avg rating in ArrayList
class SortAvgRateClass implements Comparator<Rating>{
    public int compare(Rating r1, Rating r2){
        return (int)(r2.getAverageRating()-r1.getAverageRating());
    }
}

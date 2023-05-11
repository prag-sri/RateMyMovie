package com.example.RateMyMovie.Repositories;

import com.example.RateMyMovie.DTOs.MovieWithView;
import com.example.RateMyMovie.Models.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,String> {

    @Modifying
    @Transactional
    @Query("update Movie m set m.runtimeMinutes =:#{#movie.runtimeMinutes+15} where m.tconst =:#{#movie.tconst}")
    int updateDocRunTimeMinutes(Movie movie);

    @Modifying
    @Transactional
    @Query("update Movie m set m.runtimeMinutes =:#{#movie.runtimeMinutes+30} where m.tconst =:#{#movie.tconst}")
    int updateAnimeRunTimeMinutes(Movie movie);

    @Modifying
    @Transactional
    @Query("update Movie m set m.runtimeMinutes =:#{#movie.runtimeMinutes+45} where m.tconst =:#{#movie.tconst}")
    int updateOthersRunTimeMinutes(Movie movie);

    @Query(value= " select m.genres, m.primaryTitle, r.numVotes, sum(r.numVotes) as TOTAL from movies m inner join ratings r where m.tconst= r.tconst group by m.genres;", nativeQuery=true)
    List<MovieWithView> genreMoviesWithSubtotals();
}

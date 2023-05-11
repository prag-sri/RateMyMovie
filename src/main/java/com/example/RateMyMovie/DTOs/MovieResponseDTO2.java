package com.example.RateMyMovie.DTOs;

import com.example.RateMyMovie.Models.Enums.Genre;
import lombok.Data;

@Data
public class MovieResponseDTO2 {

    private String tconst;
    private String primaryTitle;
    private float averageRating;
    private Genre genres;

    public MovieResponseDTO2(String tconst, String primaryTitle, float averageRating, Genre genres){
        this.tconst= tconst;
        this.primaryTitle= primaryTitle;
        this.averageRating= averageRating;
        this.genres= genres;
    }
}

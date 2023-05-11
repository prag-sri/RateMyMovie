package com.example.RateMyMovie.DTOs;

import com.example.RateMyMovie.Models.Enums.Genre;
import lombok.Data;

@Data
public class MovieResponseDTO1 {

    private String tconst;
    private String primaryTitle;
    private int runtimeMinutes;
    private Genre genres;

    public MovieResponseDTO1(String tconst, String primaryTitle, int runtimeMinutes, Genre genres){
        this.tconst= tconst;
        this.primaryTitle= primaryTitle;
        this.runtimeMinutes= runtimeMinutes;
        this.genres= genres;
    }
}

package com.example.RateMyMovie.DTOs;

import com.example.RateMyMovie.Models.Enums.Genre;
import com.example.RateMyMovie.Models.Enums.TitleType;
import com.example.RateMyMovie.Models.Rating;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MovieRequestDTO {

    private String tconst;
    private TitleType titleType;
    private String primaryTitle;
    private int runtimeMinutes;
    private Genre genres;
    private float averageRating;
    private int numVotes;
}

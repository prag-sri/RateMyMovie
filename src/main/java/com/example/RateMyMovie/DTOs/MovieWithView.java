package com.example.RateMyMovie.DTOs;

import com.example.RateMyMovie.Models.Enums.Genre;

public interface MovieWithView {

    String getPrimaryTitle();
    Genre getGenres();
    int getNumVotes();
    int getTotal();
}

package com.example.RateMyMovie.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="ratings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Rating {

    @Id
    @PrimaryKeyJoinColumn(name = "tconst")
    public String tconst;
    @Column(name="averageRating")
    private float averageRating;
    @Column(name="numVotes")
    private int numVotes;

}

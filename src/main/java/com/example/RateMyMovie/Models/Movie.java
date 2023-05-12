package com.example.RateMyMovie.Models;

import com.example.RateMyMovie.Models.Enums.Genre;
import com.example.RateMyMovie.Models.Enums.TitleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="movies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Movie {

    @Id
    private String tconst;
    private String titleType;
    @Column(name="primaryTitle", unique = true)
    private String primaryTitle;
    @Column(name="runtimeMinutes")
    private int runtimeMinutes;
    @Enumerated(value = EnumType.STRING)
    private Genre genres;

    @Transient
    private static int num=0;
    public static int getNum(){
        return num;
    }
    public static void setNum(){
        num= num+1;
    }

    @Transient
    private static int total=0;
    public static int getTotal(){
        return total;
    }
    public static void setTotal(int value){
        total+= value;
    }
}

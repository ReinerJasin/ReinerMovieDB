package com.example.reinermoviedb.util;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.SOURCE;

public class Constant {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/original";
    public static final String API_KEY = "00b198d77ac0dab9e4e6c2b7b6ce0c75";

    @Retention(SOURCE)
    @StringDef
    public @interface Type {
        String MOVIES = "movie";
        String TV_SHOWS = "tv";
    }
}

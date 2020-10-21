package com.example.reinermoviedb.ui.main.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.reinermoviedb.model.Cast;
import com.example.reinermoviedb.model.Genre;
import com.example.reinermoviedb.repository.MovieRepository;
import com.example.reinermoviedb.repository.TvShowRepository;

import java.util.List;

public class DetailViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private TvShowRepository tvShowRepository;

    public DetailViewModel() {
        movieRepository = MovieRepository.getInstance();
        tvShowRepository = TvShowRepository.getInstance();
    }

    public LiveData<List<Genre>> getMovieGenre(int id) {
        return movieRepository.getGenres(id);
    }

    public LiveData<List<Genre>> getTvShowGenre(int id) {
        return tvShowRepository.getGenres(id);
    }

    public LiveData<List<Cast>> getShowCast(int id) {
        return tvShowRepository.getCasts(id);
    }

    public LiveData<List<Cast>> getMovieCast(int id) {
        return movieRepository.getCasts(id);
    }

}

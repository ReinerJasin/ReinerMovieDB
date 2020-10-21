package com.example.reinermoviedb.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.reinermoviedb.model.Cast;
import com.example.reinermoviedb.model.CastResponse;
import com.example.reinermoviedb.model.Genre;
import com.example.reinermoviedb.model.GenreResponse;
import com.example.reinermoviedb.model.Movie;
import com.example.reinermoviedb.model.MovieResponse;
import com.example.reinermoviedb.network.ApiEndpoints;
import com.example.reinermoviedb.network.RetrofitService;
import com.example.reinermoviedb.util.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository movieRepository;
    private ApiEndpoints apiEndpoints;
    private RetrofitService service;
    private static final String TAG = "MovieRepository";

//    public MovieRepository(ApiEndpoints apiEndpoints) {
//        this.apiEndpoints = apiEndpoints;
//    }

    private MovieRepository() {
        service = RetrofitService.getInstance();
    }

//    public static MovieRepository getInstance() {
//        if (movieRepository == null) {
//            movieRepository = new MovieRepository(RetrofitService.createService(ApiEndpoints.class));
//        }
//        return movieRepository;
//    }


    public static MovieRepository getInstance() {
        if (movieRepository == null) {
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }


    public MutableLiveData<List<Movie>> getMovieCollection() {
        MutableLiveData<List<Movie>> listMovie = new MutableLiveData<>();

        service.getMovies().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listMovie.postValue(response.body().getResults());
                    }

                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listMovie;
    }


    public MutableLiveData<List<Genre>> getGenres(int id) {
        MutableLiveData<List<Genre>> listGenres = new MutableLiveData<>();

        service.getGenre(Constant.Type.MOVIES, id).enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getGenres().size());
                        listGenres.postValue(response.body().getGenres());
                    }
                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return listGenres;
    }

    public MutableLiveData<List<Cast>> getCasts(int id) {
        MutableLiveData<List<Cast>> listCasts = new MutableLiveData<>();

        service.getCast(Constant.Type.MOVIES, id).enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getCasts().size());
                        listCasts.postValue(response.body().getCasts());
                    }
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listCasts;
    }
}

package com.example.reinermoviedb.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.reinermoviedb.model.Movie;
import com.example.reinermoviedb.model.MovieResponse;
import com.example.reinermoviedb.network.ApiEndpoints;
import com.example.reinermoviedb.network.RetrofitService;
import com.example.reinermoviedb.util.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieRepository {
    private static MovieRepository movieRepository;
    private ApiEndpoints apiEndpoints;
    private RetrofitService service;
    private static final String TAG = "MovieRepository";

//    public MovieRepository(ApiEndpoints apiEndpoints) {
//        this.apiEndpoints = apiEndpoints;
//    }

    private MovieRepository () {
        service = RetrofitService.getInstance();
    }

//    public static MovieRepository getInstance() {
//        if (movieRepository == null) {
//            movieRepository = new MovieRepository(RetrofitService.createService(ApiEndpoints.class));
//        }
//        return movieRepository;
//    }


    public static MovieRepository getInstance(){
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
}

package com.example.reinermoviedb.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.reinermoviedb.model.Movie;
import com.example.reinermoviedb.model.MovieResponse;
import com.example.reinermoviedb.model.TvShow;
import com.example.reinermoviedb.model.TvShowResponse;
import com.example.reinermoviedb.network.ApiEndpoints;
import com.example.reinermoviedb.network.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowRepository {
    private static TvShowRepository tvShowRepository;
    private ApiEndpoints apiEndpoints;
    private RetrofitService service;
    private static final String TAG = "TvShowRepository";

//    public MovieRepository(ApiEndpoints apiEndpoints) {
//        this.apiEndpoints = apiEndpoints;
//    }

    private TvShowRepository () {
        service = RetrofitService.getInstance();
    }

//    public static MovieRepository getInstance() {
//        if (movieRepository == null) {
//            movieRepository = new MovieRepository(RetrofitService.createService(ApiEndpoints.class));
//        }
//        return movieRepository;
//    }


    public static TvShowRepository getInstance(){
        if (tvShowRepository == null) {
            tvShowRepository = new TvShowRepository();
        }
        return tvShowRepository;
    }


    public MutableLiveData<List<TvShow>> getTvShowCollection() {
        MutableLiveData<List<TvShow>> listTvShow = new MutableLiveData<>();

        service.getTvShow().enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listTvShow.postValue(response.body().getResults());
                    }

                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listTvShow;
    }
}

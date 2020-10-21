package com.example.reinermoviedb.network;

import com.example.reinermoviedb.model.CastResponse;
import com.example.reinermoviedb.model.GenreResponse;
import com.example.reinermoviedb.model.MovieResponse;
import com.example.reinermoviedb.model.TvShowResponse;
import com.example.reinermoviedb.util.Constant;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
//    private static Retrofit retrofit;
//
//    public static <S> S createService(Class<S> serviceClass){
//        if (retrofit == null){
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(Constant.BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit.create(serviceClass);
//    }

    private ApiEndpoints api;
    private static RetrofitService service;

    private RetrofitService() {
        api = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiEndpoints.class);
    }

    public static RetrofitService getInstance() {
        if (service == null) {
            service = new RetrofitService();
        }
        return service;
    }

    public Call<MovieResponse> getMovies() {
        return api.getMovies(Constant.API_KEY);
    }

    public Call<TvShowResponse> getTvShow() {
        return api.getTvShow(Constant.API_KEY);
    }

    public Call<GenreResponse> getGenre(String type, int id) {
        return api.getGenres(type, id, Constant.API_KEY);
    }

    public Call<CastResponse> getCast(String type, int id) {
        return api.getCasts(type, id, Constant.API_KEY);
    }
}
